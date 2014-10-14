package com.demo.web.controllers;

import javax.servlet.http.HttpServletRequest;

import com.demo.entities.User;

public abstract class BaseController {

	/**
	 * 获取保存在Session中的用户对象
	 * 
	 * @param request
	 * @return
	 */
	protected User getSessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute("user_context");
	}

	/**
	 * 保存用户对象到Session中
	 * 
	 * @param request
	 * @param user
	 */
	protected void setSessionUser(HttpServletRequest request, User user) {
		request.getSession().setAttribute("user_context", user);
	}

}
