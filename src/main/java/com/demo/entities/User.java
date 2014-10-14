package com.demo.entities;

import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.type.Alias;

/**
 * 用户表
 * 
 * @author Administrator
 * 
 */
@Alias("User")
public class User extends BaseEntity {
	private static final long serialVersionUID = -1547458917316008286L;
	/**
	 * 用户ID
	 */
	private int user_id;
	/**
	 * 用户名
	 */
	private String user_name;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 用户类型(1:普通用户，2:管理员)
	 */
	private int user_type;
	/**
	 * 用户锁定(1:未锁定，2:锁定)
	 */
	private int locked;
	/**
	 * 积分
	 */
	private int credit = 0;
	/**
	 * 用户管理版块
	 */
	private Set<Board> manBoards = new HashSet<Board>();
	/**
	 * 最后登录信息
	 */
	private LoginLog loginLog = new LoginLog();

	public User() {
	}

	public int getUser_id() {
		return user_id;
	}

	public User setUser_id(int user_id) {
		this.user_id = user_id;
		return this;
	}

	public String getUser_name() {
		return user_name;
	}

	public User setUser_name(String user_name) {
		this.user_name = user_name;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public int getUser_type() {
		return user_type;
	}

	public User setUser_type(int user_type) {
		this.user_type = user_type;
		return this;
	}

	public int getLocked() {
		return locked;
	}

	public User setLocked(int locked) {
		this.locked = locked;
		return this;
	}

	public int getCredit() {
		return credit;
	}

	public User setCredit(int credit) {
		this.credit = credit;
		return this;
	}

	public Set<Board> getManBoards() {
		return manBoards;
	}

	public User setManBoards(Set<Board> manBoards) {
		this.manBoards = manBoards;
		return this;
	}

	public LoginLog getLoginLog() {
		return loginLog;
	}

	public User setLoginLog(LoginLog loginLog) {
		this.loginLog = loginLog;
		return this;
	}
}
