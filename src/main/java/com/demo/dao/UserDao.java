package com.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.demo.common.emun.OrderBy;
import com.demo.dao.mybatis.UserMapper;
import com.demo.entities.Page;
import com.demo.entities.User;

@Repository(value = "UserDao")
public class UserDao {
	private SqlSessionTemplate sqlsession;

	/**
	 * 查询单个用户信息(必须提供user_id或user_name)
	 * 
	 * @param t
	 * @return 用户信息或null
	 */
	public User get(User t) {
		if (t != null
				&& (t.getUser_id() != 0 || (t.getUser_name() != null && !""
						.equals(t.getUser_name())))) {
			return sqlsession.getMapper(UserMapper.class).get(t);
		} else {
			String mess = null;
			if (t == null) {
				mess = "传入参数不能为 null";
			} else {
				mess = "必须提供user_id或user_name作为查询条件";
			}
			throw new IllegalArgumentException(mess);
		}
	}

	public int IncreaseCredit(int userid, int increaseCredit) {
		if (userid == 0) {
			throw new IllegalArgumentException("必须提供user_id作为查询条件");
		}
		return sqlsession.getMapper(UserMapper.class).IncreaseCredit(userid,
				increaseCredit);
	}

	/**
	 * 查询单个用户信息(根据user_id)
	 * 
	 * @param id
	 * @return 用户信息或null
	 */
	public User getByID(int id) {
		return this.get(new User().setUser_id(id));
	}

	/**
	 * 查询单个用户信息(根据user_name)
	 * 
	 * @param userName
	 * @return 用户信息或null
	 */
	public User getByUserName(String userName) {
		return this.get(new User().setUser_name(userName));
	}

	/**
	 * 返回所有记录数
	 * 
	 * @return 记录数
	 */
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
		return sqlsession.getMapper(UserMapper.class).getCount(condition);
	}

	/**
	 * 返回所有用户信息
	 * 
	 * @return 结果集
	 */
	public List<User> findAll() {
		return this.find(null, null, null);
	}

	/**
	 * 分页查询用户信息
	 * 
	 * @param page
	 *            分页实体
	 * @return 结果集
	 */
	public List<User> findByPage(Page page) {
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
	public List<User> findByPageAndOrderBy(Page page, String orderName,
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
	public List<User> findByPageAndOrderBy(Page page,
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
	public List<User> findByCondition(Map<String, Object> condition) {
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
	public List<User> findByConditionAndOrder(Map<String, Object> condition,
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
	public List<User> findByConditionAndOrder(Map<String, Object> condition,
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
	public List<User> findByPageAndCondition(Page page,
			Map<String, Object> condition) {
		return this.find(page, condition, null);
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
	public List<User> find(Page page, Map<String, Object> condition,
			Map<String, OrderBy> orderBy) {
		if (page != null) {
			page.update(this.getCount(condition));
		}
		return sqlsession.getMapper(UserMapper.class).find(page, condition,
				orderBy);
	}

	/**
	 * 添加方法
	 * 
	 * @param t
	 *            用户实体(user_name、password、user_type、locked都不能为空)
	 * @return 1成功，-1失败
	 */
	public int add(User t) {
		if (t != null && t.getUser_name() != null
				&& !"".equals(t.getUser_name()) && t.getPassword() != null
				&& !"".equals(t.getPassword()) && t.getUser_type() != 0
				&& t.getLocked() != 0) {
			return sqlsession.getMapper(UserMapper.class).add(t);
		} else {
			String mess = null;
			if (t == null) {
				mess = "传入参数不能为 null";
			} else {
				mess = "必须提供user_name、password、user_type、locked进行数据插入";
			}
			throw new IllegalArgumentException(mess);
		}
	}

	/**
	 * 更新方法
	 * 
	 * @param t
	 *            用户实体(必须提供user_id或user_name且必须更新最少一个字段)
	 * @return 1成功,-1失败
	 */
	public int update(User t) {
		if (t != null
				&& (t.getUser_id() != 0 || (t.getUser_name() != null && !""
						.equals(t.getUser_name())))
				&& ((t.getPassword() != null && !"".equals(t.getPassword()))
						|| t.getUser_type() != 0 || t.getLocked() != 0 || t
						.getCredit() != 0)) {
			return sqlsession.getMapper(UserMapper.class).update(t);
		} else {
			String mess = null;
			if (t == null) {
				mess = "传入参数不能为 null";
			} else if (t.getUser_id() == 0
					|| (t.getUser_name() == null && "".equals(t.getUser_name()))) {
				mess = "必须提供user_id或user_name作为查询条件";
			} else {
				mess = "必须提供password、user_type、locked之中一个或多个进行修改";
			}
			throw new IllegalArgumentException(mess);
		}
	}

	/**
	 * 删除方法
	 * 
	 * @param t
	 *            用户实体(必须提供user_id或user_name)
	 * @return 1成功,-1失败
	 */
	public int delete(User t) {
		if (t != null && t.getUser_id() != 0 && t.getUser_name() != null
				&& !"".equals(t.getUser_name())) {
			return sqlsession.getMapper(UserMapper.class).delete(t);
		} else {
			String mess = null;
			if (t == null) {
				mess = "传入参数不能为 null";
			} else {
				mess = "必须提供user_id或user_name作为查询条件";
			}
			throw new IllegalArgumentException(mess);
		}
	}

	public SqlSessionTemplate getSqlsession() {
		return sqlsession;
	}

	@Resource(name = "mybatisSession")
	public void setSqlsession(SqlSessionTemplate sqlsession) {
		this.sqlsession = sqlsession;
	}

}
