package com.hr.management.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class LogginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Strt");
		// Continue the filter chain with the wrapped request and original response
		HttpServletRequest httpServletRequest =(HttpServletRequest)request;
		Collections.list(httpServletRequest.getParameterNames())
        .forEach(paramName -> System.out.println(paramName + ": " + httpServletRequest.getParameter(paramName)));
		chain.doFilter(new XSSFilterWrapper((HttpServletRequest) request), response);
		System.out.println("End");

	}

}
