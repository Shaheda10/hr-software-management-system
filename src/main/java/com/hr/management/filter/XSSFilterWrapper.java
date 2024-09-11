package com.hr.management.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSFilterWrapper extends HttpServletRequestWrapper {

	public XSSFilterWrapper(HttpServletRequest request) {
		super(request);
		System.out.println("Inside Constructor");
	}

}
