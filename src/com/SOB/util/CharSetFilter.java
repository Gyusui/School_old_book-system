package com.SOB.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CharSetFilter implements Filter{

	String charSetName;
	public void init(FilterConfig config)throws ServletException{
		charSetName = config.getInitParameter("charset");
	}
	
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws ServletException,IOException{
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		httpRequest.setCharacterEncoding(charSetName);
		
		chain.doFilter(request, response);
	}
	
	public void destroy(){
		
	}
}
