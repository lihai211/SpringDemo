package com.demo.entities;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 登录日志表
 * 
 * @author Administrator
 * 
 */
@Alias("LoginLog")
public class LoginLog extends BaseEntity {

	private static final long serialVersionUID = -7173564577215893490L;
	/**
	 * 日志id
	 */
	private int login_log_id;
	/**
	 * 用户ID
	 */
	private int user_id;
	/**
	 * 用户登录ip
	 */
	private String ip;
	/**
	 * 用户登录时间
	 */
	private Date login_datetime;

	public int getLogin_log_id() {
		return login_log_id;
	}

	public LoginLog setLogin_log_id(int login_log_id) {
		this.login_log_id = login_log_id;
		return this;
	}

	public int getUser_id() {
		return user_id;
	}

	public LoginLog setUser_id(int user_id) {
		this.user_id = user_id;
		return this;
	}

	public String getIp() {
		return ip;
	}

	public LoginLog setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public Date getLogin_datetime() {
		return login_datetime;
	}

	public LoginLog setLogin_datetime(Date login_datetime) {
		this.login_datetime = login_datetime;
		return this;
	}
}
