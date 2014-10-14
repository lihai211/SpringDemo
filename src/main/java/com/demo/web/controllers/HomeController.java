package com.demo.web.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.service.ForumService;

@Controller
public class HomeController {
	private ForumService fs;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView view = new ModelAndView();
		view.setViewName("index");
		view.addObject("boards", fs.getAllBoard());
		return view;
	}

	@Resource(name = "ForumService")
	public void setFs(ForumService fs) {
		this.fs = fs;
	}
}
