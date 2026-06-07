package com.hospital.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoggingFilter implements Filter {

        private static final Logger logger = LogManager.getLogger(LoggingFilter.class);

        @Override
        public void doFilter(ServletRequest request,
                        ServletResponse response,
                        FilterChain chain)
                        throws IOException, ServletException {

                HttpServletRequest req = (HttpServletRequest) request;

                logger.info("REQUEST: {} {}", req.getMethod(), req.getRequestURI());

                chain.doFilter(request, response);

                logger.info("RESPONSE: {} {}", req.getMethod(), req.getRequestURI());
        }
}