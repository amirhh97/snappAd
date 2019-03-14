package com.snappad.Filters;

import Utility.TokenUtility;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/ad/reg")
public class TokenCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Token Checking is on .........................");
        String token = "";
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        token = request.getHeader("Authorization");
        if (token != null && !token.equals("") && TokenUtility.isValid(token))
            filterChain.doFilter(servletRequest, servletResponse);
        else {
            response.sendError(401, "Refresh Token needed");
        }

    }

    @Override
    public void destroy() {

    }
}
