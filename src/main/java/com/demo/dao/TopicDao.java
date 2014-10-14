package com.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.demo.common.emun.OrderBy;
import com.demo.dao.mybatis.TopicMapper;
import com.demo.entities.Page;
import com.demo.entities.Topic;

@Repository(value = "TopicDao")
public class TopicDao {
	private SqlSessionTemplate sqlsession;

	public List<Topic> findByPageAndBoard_ID(int board_id, Page page) {
		if (board_id != 0 && page != null) {
			Map<String, Object> con = new HashMap<String, Object>();
			con.put("board_id", board_id);
			return this.findByPageAndCondition(page, con);
		} else {
			throw new IllegalArgumentException("必须提供board_id或page对象不能为null");
		}
	}

	public int getCount() {
		return this.getCount(null);
	}

	/**
	 * 根据条件返回记录数
	 * 
	 * @param conditionSql
	 *            where之后的条件，不需要带where
	 * @return 记录数
	 */
	public int getCount(Map<String, Object> condition) {
		return sqlsession.getMapper(TopicMapper.class).getCount(condition);
	}

	/**
	 * 根据分页、条件、多排序条件查询
	 * 
	 * @param page
	 *            分页实体
	 * @param conditionSql
	 *            where之后的条件，不需要带where
	 * @param orderBy
	 *            多个排序条件,key:字段名、value:排序条件
	 * @return 结果集
	 */
	public List<Topic> find(Page page, Map<String, Object> condition,
			Map<String, OrderBy> orderBy) {
		if (page != null) {
			page.update(this.getCount(condition));
		}
		return sqlsession.getMapper(TopicMapper.class).find(page, condition,
				orderBy);
	}

	/**
	 * 返回所有结果集
	 * 
	 * @return
	 */
	public List<Topic> findAll() {
		return this.find(null, null, null);
	}

	/**
	 * 分页查询用户信息
	 * 
	 * @param page
	 *            分页实体
	 * @return 结果集
	 */
	public List<Topic> findByPage(Page page) {
		return this.find(page, null, null);
	}

	/**
	 * 分页查询根据单个排序条件
	 * 
	 * @param page
	 *            分页实体
	 * @param orderName
	 *            排序字段名
	 * @param by
	 *            排序条件
	 * @return 结果集
	 */
	public List<Topic> findByPageAndOrderBy(Page page, String orderName,
			OrderBy by) {
		Map<String, OrderBy> orderBy = new HashMap<String, OrderBy>();
		orderBy.put(orderName, by);
		return this.findByPageAndOrderBy(page, orderBy);
	}

	/**
	 * 分页查询根据多个排序条件
	 * 
	 * @param page
	 *            分页实体
	 * @param orderBy
	 *            多个排序条件,key:字段名、value:排序条件
	 * @return 结果集
	 */
	public List<Topic> findByPageAndOrderBy(Page page,
			Map<String, OrderBy> orderBy) {
		return this.find(page, null, orderBy);
	}

	/**
	 * 根据条件查询
	 * 
	 * @param conditionSql
	 *            where之后的条件，不需要带where
	 * @return 结果集
	 */
	public List<Topic> findByCondition(Map<String, Object> condition) {
		return this.find(null, condition, null);
	}

	/**
	 * 根据条件与单条件排序查询
	 * 
	 * @param conditionSql
	 *            where之后的条件，不需要带where
	 * @param orderName
	 *            排序字段名
	 * @param by
	 *            排序条件
	 * @return 结果集
	 */
	public List<Topic> findByConditionAndOrder(Map<String, Object> condition,
			String orderName, OrderBy by) {
		Map<String, OrderBy> orderBy = new HashMap<String, OrderBy>();
		orderBy.put(orderName, by);
		return this.find(null, condition, orderBy);
	}

	/**
	 * 根据条件与多条件排序查询
	 * 
	 * @param conditionSql
	 *            where之后的条件，不需要带where
	 * @param orderBy
	 *            多个排序条件,key:字段名、value:排序条件
	 * @return 结果集
	 */
	public List<Topic> findByConditionAndOrder(Map<String, Object> condition,
			Map<String, OrderBy> orderBy) {
		return this.find(null, condition, orderBy);
	}

	/**
	 * 根据分页与条件查询
	 * 
	 * @param page
	 *            分页实体
	 * @param conditionSql
	 *            where之后的条件，不需要带where
	 * @return 结果集
	 */
	public List<Topic> findByPageAndCondition(Page page,
			Map<String, Object> condition) {
		return this.find(page, condition, null);
	}

	@Resource(name = "mybatisSession")
	public void setSqlsession(SqlSessionTemplate sqlsession) {
		this.sqlsession = sqlsession;
	}
}
