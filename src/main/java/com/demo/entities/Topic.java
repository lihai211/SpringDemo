package com.demo.entities;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 主题表
 * 
 * @author Administrator
 * 
 */
@Alias("Topic")
public class Topic extends BaseEntity {

	private static final long serialVersionUID = -4540743116535513845L;
	/**
	 * 精华主题帖子
	 */
	public static final int DIGEST_TOPIC = 1;
	/**
	 * 普通的主题帖子
	 */
	public static final int NOT_DIGEST_TOPIC = 0;
	/**
	 * 帖子id
	 */
	private int topic_id;
	/**
	 * 所属版块id
	 */
	private int board_id;
	/**
	 * 帖子标题
	 */
	private String topic_title;
	/**
	 * 发贴用户id
	 */
	private int user_id;
	/**
	 * 发贴时间
	 */
	private Date create_time;
	/**
	 * 最后回复时间
	 */
	private Date last_post;
	/**
	 * 浏览数
	 */
	private int topic_views;
	/**
	 * 回复数
	 */
	private int topic_replies;
	/**
	 * 是否精华贴(0:不是，1:是)
	 */
	private int digest;
	/**
	 * 
	 */
	private User user;
	/**
	 * 
	 */
	private MainPost mainPost;

	public int getTopic_id() {
		return topic_id;
	}

	public Topic setTopic_id(int topic_id) {
		this.topic_id = topic_id;
		return this;
	}

	public int getBoard_id() {
		return board_id;
	}

	public Topic setBoard_id(int board_id) {
		this.board_id = board_id;
		return this;
	}

	public String getTopic_title() {
		return topic_title;
	}

	public Topic setTopic_title(String topic_title) {
		this.topic_title = topic_title;
		return this;
	}

	public int getUser_id() {
		return user_id;
	}

	public Topic setUser_id(int user_id) {
		this.user_id = user_id;
		return this;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public Topic setCreate_time(Date create_time) {
		this.create_time = create_time;
		return this;
	}

	public Date getLast_post() {
		return last_post;
	}

	public Topic setLast_post(Date last_post) {
		this.last_post = last_post;
		return this;
	}

	public int getTopic_views() {
		return topic_views;
	}

	public Topic setTopic_views(int topic_views) {
		this.topic_views = topic_views;
		return this;
	}

	public int getTopic_replies() {
		return topic_replies;
	}

	public Topic setTopic_replies(int topic_replies) {
		this.topic_replies = topic_replies;
		return this;
	}

	public int getDigest() {
		return digest;
	}

	public Topic setDigest(int digest) {
		this.digest = digest;
		return this;
	}

	public User getUser() {
		return user;
	}

	public Topic setUser(User user) {
		this.user = user;
		return this;
	}

	public MainPost getMainPost() {
		return mainPost;
	}

	public void setMainPost(MainPost mainPost) {
		this.mainPost = mainPost;
	}

}
