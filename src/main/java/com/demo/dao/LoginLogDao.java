package com.demo.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.demo.dao.mybatis.LoginLogMapper;
import com.demo.entities.LoginLog;

@Repository(value = "LoginLogDao")
public class LoginLogDao {
	private SqlSessionTemplate sqlsession;

	public int add(int userid, String ip) {
		if (userid == 0 || ip == null || "".equals(ip)) {
			throw new IllegalArgumentException("必须提供user_id与ip进行数据插入");
		}
		return this.getSqlsession().getMapper(LoginLogMapper.class)
				.add(new LoginLog().setUser_id(userid).setIp(ip));
	}

	public SqlSessionTemplate getSqlsession() {
		return sqlsession;
	}

	@Resource(name = "mybatisSession")
	public void setSqlsession(SqlSessionTemplate sqlsession) {
		this.sqlsession = sqlsession;
	}
}
