package com.demo.dao.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.common.emun.OrderBy;
import com.demo.entities.Page;

public interface BaseMapper<T> {
	/**
	 * 查找
	 * 
	 * @param id
	 * @return
	 */
	public abstract T get(@Param("t") T t);

	/**
	 * 返回条件总记录数
	 * 
	 * @param conditionSql
	 *            条件sql语句
	 * @return
	 */
	public abstract int getCount(
			@Param("condition") Map<String, Object> condition);

	/**
	 * 根据条件查找
	 * 
	 * @param page
	 *            分页
	 * @param condition
	 *            查询条件
	 * @param orderBy
	 *            多条件排序
	 * @return
	 */
	public abstract List<T> find(@Param("page") Page page,
			@Param("condition") Map<String, Object> condition,
			@Param("orderBy") Map<String, OrderBy> orderBy);

	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	public abstract int add(@Param("t") T t);

	/**
	 * 更新
	 * 
	 * @param user
	 * @return
	 */
	public abstract int update(@Param("t") T t);

	/**
	 * 删除
	 * 
	 * @param user
	 * @return
	 */
	public abstract int delete(@Param("t") T t);
}
