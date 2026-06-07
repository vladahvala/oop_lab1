package com.hospital.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        System.out.println(
                "[REQUEST] "
                        + req.getMethod()
                        + " "
                        + req.getRequestURI());

        chain.doFilter(request, response);

        System.out.println(
                "[RESPONSE] "
                        + req.getMethod()
                        + " "
                        + req.getRequestURI());
    }
}