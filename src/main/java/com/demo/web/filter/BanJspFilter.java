package com.demo.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤jsp目录下的访问
 * 
 * @author wu
 * 
 */
public class BanJspFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		res.setStatus(HttpServletResponse.SC_NOT_FOUND);// 设置状态码为404
		res.sendError(HttpServletResponse.SC_NOT_FOUND);// 设置错误信息为404
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
