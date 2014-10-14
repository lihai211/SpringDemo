package com.demo.web.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demo.entities.Board;
import com.demo.entities.Page;
import com.demo.service.ForumService;

@Controller
public class BoardController extends BaseController {
	private ForumService fs;

	@RequestMapping(value = "/board/list/{boardid}", method = RequestMethod.GET)
	public ModelAndView getBoard(
			@PathVariable(value = "boardid") String boardid,
			@RequestParam(value = "pn", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();
		Board board = fs.getBoard(Integer.parseInt(boardid));
		if (board != null) {
			view.addObject("board", board);
			Page page = new Page().setCurPage(pageNo == null ? 1 : pageNo);
			view.addObject("topics",
					fs.getAllTopicByBoard(board.getBoard_id(), page));
			view.addObject("page", page);
		}
		view.setViewName("board");
		return view;
	}

	@Resource(name = "ForumService")
	public void setFs(ForumService fs) {
		this.fs = fs;
	}

}
