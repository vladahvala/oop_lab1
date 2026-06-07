package com.hospital.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(AuthFilter.class);
    private static final String SECRET = "secret123";

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // CORS
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        String uri = req.getRequestURI();

        // login пропускаємо
        if (uri.contains("/auth/")) {
            chain.doFilter(request, response);
            return;
        }

        String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("No token provided");
            return;
        }

        try {
            String token = header.substring(7);

            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET))
                    .build()
                    .verify(token);

            String role = jwt.getClaim("role").asString();

            logger.info("Role from token: {} accessing {}", role, uri);

            if ("DOCTOR".equals(role)) {
                chain.doFilter(request, response);
                return;
            }

            if ("NURSE".equals(role)) {

                boolean blocked = uri.contains("/doctors") ||
                        uri.contains("/diagnoses") ||
                        (uri.contains("/patients") && req.getMethod().equals("POST"));

                if (blocked) {
                    resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    resp.getWriter().write("Nurse not allowed");
                    return;
                }

                chain.doFilter(request, response);
                return;
            }

            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Invalid role");

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Invalid token");
        }
    }
}