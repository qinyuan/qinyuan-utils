package com.qinyuan15.utils.security;

import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

public class ServletPathRequestMatcher implements RequestMatcher {
    private final String servletPath;
    public ServletPathRequestMatcher(String servletPath) {
        this.servletPath = servletPath;
    }
    @Override
    public boolean matches(HttpServletRequest request) {
        return servletPath != null && request.getServletPath().equals(servletPath);
    }
}
