package com.example.hcms.security;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RBACFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String role = req.getHeader("Role"); // For demo, get role from header

        String path = req.getRequestURI();

        // Example: Only allow ADMIN to access /api/admin/*
        if (path.startsWith("/api/admin") && !"ADMIN".equalsIgnoreCase(role)) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            res.getWriter().write("{\"error\":\"Forbidden: ADMIN role required.\"}");
            return;
        }

        // For other paths, allow access
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
}