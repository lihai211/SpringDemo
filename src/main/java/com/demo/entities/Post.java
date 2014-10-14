package com.demo.entities;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 帖子表
 * 
 * @author Administrator
 * 
 */
@Alias("Post")
public class Post extends BaseEntity {

	private static final long serialVersionUID = -2185907262318578605L;
	/**
	 * 帖子id
	 */
	private int post_id;
	/**
	 * 版块id
	 */
	private int board_id;
	/**
	 * 主题id
	 */
	private int topic_id;
	/**
	 * 用户id
	 */
	private int user_id;
	/**
	 * 帖子类型(1:主帖子,2:回复帖子)
	 */
	private int post_type;
	/**
	 * 帖子标题
	 */
	private String post_title;
	/**
	 * 帖子内容
	 */
	private String post_text;
	/**
	 * 创建时间
	 */
	private Date create_time;

	private User user;
	private Topic topic;

	public int getPost_id() {
		return post_id;
	}

	public Post setPost_id(int post_id) {
		this.post_id = post_id;
		return this;
	}

	public int getBoard_id() {
		return board_id;
	}

	public Post setBoard_id(int board_id) {
		this.board_id = board_id;
		return this;
	}

	public int getTopic_id() {
		return topic_id;
	}

	public Post setTopic_id(int topic_id) {
		this.topic_id = topic_id;
		return this;
	}

	public int getUser_id() {
		return user_id;
	}

	public Post setUser_id(int user_id) {
		this.user_id = user_id;
		return this;
	}

	public int getPost_type() {
		return post_type;
	}

	public Post setPost_type(int post_type) {
		this.post_type = post_type;
		return this;
	}

	public String getPost_title() {
		return post_title;
	}

	public Post setPost_title(String post_title) {
		this.post_title = post_title;
		return this;
	}

	public String getPost_text() {
		return post_text;
	}

	public Post setPost_text(String post_text) {
		this.post_text = post_text;
		return this;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public Post setCreate_time(Date create_time) {
		this.create_time = create_time;
		return this;
	}

	public Topic getTopic() {
		return topic;
	}

	public Post setTopic(Topic topic) {
		this.topic = topic;
		return this;
	}

	public User getUser() {
		return user;
	}

	public Post setUser(User user) {
		this.user = user;
		return this;
	}
}
