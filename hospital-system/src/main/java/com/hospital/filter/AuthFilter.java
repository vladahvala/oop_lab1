package com.hospital.filter;

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

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // CORS (ТІЛЬКИ ТУТ!)
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");

        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        String role = req.getHeader("Authorization");
        String uri = req.getRequestURI();

        logger.info("Role: {} accessing {}", role, uri);

        if (role == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("No role provided");
            return;
        }

        if (role.equals("DOCTOR")) {
            chain.doFilter(request, response);
            return;
        }

        if (role.equals("NURSE")) {

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
    }
}