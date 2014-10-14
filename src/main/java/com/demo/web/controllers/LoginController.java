package com.demo.web.controllers;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.entities.LoginLog;
import com.demo.entities.User;
import com.demo.service.UserService;
import com.demo.util.MD5Tool;

@Controller
public class LoginController extends BaseController {
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject login(HttpServletRequest request,
			@RequestParam("u") String username,
			@RequestParam("p") String password) {
		JSONObject json = new JSONObject();
		if (username == null || "".equals(username) || password == null
				|| "".equals(password)) {
			json.put("code", -1);
		} else {
			User user = new User()
					.setUser_name(username)
					.setPassword(
							MD5Tool.codec(username + MD5Tool.codec(password)))
					.setLoginLog(new LoginLog().setIp(request.getRemoteAddr()));
			int code = this.userService.login(user);
			switch (code) {
			case 1:
				json.put("code", 1);
				this.setSessionUser(request, user);
				break;
			case 2:
				json.put("code", 2);
				break;
			default:
				json.put("code", -1);
				break;
			}
		}
		return json;
	}

	@RequestMapping(value = "/loginout", method = RequestMethod.GET)
	public String loginout(HttpServletRequest request) {
		Enumeration<String> en = request.getSession().getAttributeNames();
		while (en.hasMoreElements()) {
			request.getSession().removeAttribute(en.nextElement());
		}
		return "redirect:/";
	}

	@Resource(name = "UserService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
