package com.demo.dao.mybatis;

import org.apache.ibatis.annotations.Param;

import com.demo.entities.Board;

public interface BoardMapper extends BaseMapper<Board> {
	public abstract int updateBoardNumIncrement(@Param("b") Board board);
}
