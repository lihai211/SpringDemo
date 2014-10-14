package com.demo.entities;

import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.type.Alias;

/**
 * 论坛版块表
 * 
 * @author Administrator
 * 
 */
@Alias("Board")
public class Board extends BaseEntity {

	private static final long serialVersionUID = -6530587406897074386L;
	/**
	 * 论坛版块ID
	 */
	private int board_id;
	/**
	 * 论坛版块名
	 */
	private String board_name;
	/**
	 * 论坛版块描述
	 */
	private String board_desc;
	/**
	 * 帖子数目
	 */
	private int board_num;
	/**
	 * 版块管理员
	 */
	private Set<User> users = new HashSet<User>();

	public Board() {
	}

	public Board(int board_id, String board_name, String board_desc,
			int board_num) {
		this.board_id = board_id;
		this.board_name = board_name;
		this.board_desc = board_desc;
		this.board_num = board_num;
	}

	public int getBoard_id() {
		return board_id;
	}

	public Board setBoard_id(int board_id) {
		this.board_id = board_id;
		return this;
	}

	public String getBoard_name() {
		return board_name;
	}

	public Board setBoard_name(String board_name) {
		this.board_name = board_name;
		return this;
	}

	public String getBoard_desc() {
		return board_desc;
	}

	public Board setBoard_desc(String board_desc) {
		this.board_desc = board_desc;
		return this;
	}

	public int getBoard_num() {
		return board_num;
	}

	public Board setBoard_num(int board_num) {
		this.board_num = board_num;
		return this;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
