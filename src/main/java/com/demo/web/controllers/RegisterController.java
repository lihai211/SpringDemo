package com.demo.web.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.entities.User;
import com.demo.service.UserService;
import com.demo.util.MD5Tool;

@Controller
public class RegisterController extends BaseController {
	private UserService userService;

	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String register() {
		return "regist";
	}

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject register(HttpServletRequest request,
			@RequestParam("ru") String username,
			@RequestParam("rp") String password) {
		JSONObject json = new JSONObject();
		if (username == null || "".equals(username) || password == null
				|| "".equals(password)) {
			json.put("code", -1);
		} else {
			User user = new User().setUser_name(username).setPassword(
					MD5Tool.codec(username + MD5Tool.codec(password)));
			int code = userService.regist(user);
			switch (code) {
			case 1:
				json.put("code", 1);
				this.setSessionUser(request, user);
				break;
			case -1:
				json.put("code", -1);
				break;
			default:
				json.put("code", -1);
				break;
			}
		}
		return json;
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject check(@RequestParam("u") String username) {
		JSONObject json = new JSONObject();
		if (username != null) {
			json.put("code", userService.checkUserName(username));
		} else {
			json.put("code", -1);
		}
		return json;
	}

	@Resource(name = "UserService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
