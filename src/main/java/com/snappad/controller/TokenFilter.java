package com.snappad.controller;
import java.io.IOException;

import javax.servlet.*;
public class TokenFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Token Filter ");
			chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
