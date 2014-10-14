package com.demo.dao.mybatis;

import org.apache.ibatis.annotations.Param;

import com.demo.entities.User;

public interface UserMapper extends BaseMapper<User> {
	public abstract int IncreaseCredit(@Param("userid") int userid,
			@Param("credit") int increaseCredit);

}
