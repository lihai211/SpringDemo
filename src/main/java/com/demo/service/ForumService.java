package com.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.dao.BoardDao;
import com.demo.dao.TopicDao;
import com.demo.entities.Board;
import com.demo.entities.Page;
import com.demo.entities.Topic;

@Service(value = "ForumService")
public class ForumService {
	private BoardDao boardDao;
	private TopicDao topicDao;

	public List<Board> getAllBoard() {
		return boardDao.findAll();
	}

	public Board getBoard(int id) {
		return boardDao.getByID(id);
	}

	public List<Topic> getAllTopicByBoard(int board_id, Page page) {
		return topicDao.findByPageAndBoard_ID(board_id, page);
	}

	@Resource(name = "BoardDao")
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Resource(name = "TopicDao")
	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}

}
