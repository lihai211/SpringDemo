package com.demo.web.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.service.ForumService;

@Controller
public class TopicController extends BaseController {
	private ForumService fs;

	@RequestMapping(value = "/topic/addtopic", method = RequestMethod.GET)
	public String toAddTopic() {
		return "addtopic";
	}

	@RequestMapping(value = "/topic/addtopic", method = RequestMethod.POST)
	public ModelAndView addTopic() {
		ModelAndView view = new ModelAndView();
		return view;
	}

	@Resource(name = "ForumService")
	public void setFs(ForumService fs) {
		this.fs = fs;
	}
}
