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

        String role = req.getHeader("Authorization");
        String uri = req.getRequestURI();

        logger.info("Role: {} accessing {}", role, uri);

        if (role == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("No role provided");
            return;
        }

        // 🟡 DOCTOR — все дозволено
        if (role.equals("DOCTOR")) {
            chain.doFilter(request, response);
            return;
        }

        // 🟡 NURSE — обмеження
        if (role.equals("NURSE")) {

            boolean blocked = uri.contains("/doctors") ||
                    uri.contains("/diagnoses") ||
                    uri.contains("/patients") && req.getMethod().equals("POST");

            if (blocked) {
                logger.warn("NURSE blocked access to: " + uri);

                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                resp.getWriter().write("Nurse not allowed");
                return;
            }

            chain.doFilter(request, response);
            return;
        }

        // ❌ невідома роль
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        resp.getWriter().write("Invalid role");
    }
}