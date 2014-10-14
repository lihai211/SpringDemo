package com.demo.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.demo.common.emun.OrderBy;
import com.demo.dao.mybatis.BoardMapper;
import com.demo.entities.Board;
import com.demo.entities.Page;

@Repository(value = "BoardDao")
public class BoardDao {
	private SqlSessionTemplate sqlsession;

	/**
	 * 查询单个版块信息
	 * 
	 * @param b
	 * @return
	 */
	public Board get(Board b) {
		if (b != null && b.getBoard_id() != 0
				&& (b.getBoard_name() != null || !"".equals(b.getBoard_name()))) {
			return sqlsession.getMapper(BoardMapper.class).get(b);
		} else {
			String mess = null;
			if (b == null) {
				mess = "传入参数不能为 null";
			} else {
				mess = "必须提供board_id或board_name作为查询条件";
			}
			throw new IllegalArgumentException(mess);
		}
	}

	/**
	 * 查询单个版块信息(根据board_id)
	 * 
	 * @param id
	 * @return 版块信息或null
	 */
	public Board getByID(int id) {
		return this.get(new Board().setBoard_id(id));
	}

	/**
	 * 查询单个版块信息(根据board_name)
	 * 
	 * @param userName
	 * @return 版块信息或null
	 */
	public Board getByBoardName(String boardName) {
		return this.get(new Board().setBoard_name(boardName));
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
		return sqlsession.getMapper(BoardMapper.class).getCount(condition);
	}

	/**
	 * 添加版块方法
	 * 
	 * @param b
	 *            版块实体
	 * @return 1成功,-1失败
	 */
	public int add(Board b) {
		if (b != null && b.getBoard_name() != null
				&& !"".equals(b.getBoard_name())) {
			return sqlsession.getMapper(BoardMapper.class).add(b);
		} else {
			String mess = null;
			if (b == null) {
				mess = "传入参数不能为 null";
			} else {
				mess = "必须提供board_name进行数据插入";
			}
			throw new IllegalArgumentException(mess);
		}
	}

	/**
	 * 更新版块方法
	 * 
	 * @param b
	 *            版块实体
	 * @return 1成功,-1失败
	 */
	public int update(Board b) {
		if (b != null
				&& b.getBoard_id() != 0
				&& ((b.getBoard_name() != null && !"".equals(b.getBoard_name())) || (b
						.getBoard_desc() != null && !"".equals(b
						.getBoard_desc())))) {
			return sqlsession.getMapper(BoardMapper.class).update(b);
		} else {
			String mess = null;
			if (b == null) {
				mess = "传入参数不能为 null";
			} else if (b.getBoard_id() == 0) {
				mess = "必须提供board_id作为查询的条件";
			} else {
				mess = "必须提供board_name、board_desc之中一个或多个进行修改";
			}
			throw new IllegalArgumentException(mess);
		}
	}

	/**
	 * 更新版块帖子数(自增)
	 * 
	 * @param b
	 *            版块实体
	 * 
	 * @return 1成功,-1失败
	 */
	public int updateBoardNumIncrement(Board b) {
		if (b != null && b.getBoard_id() != 0) {
			return sqlsession.getMapper(BoardMapper.class)
					.updateBoardNumIncrement(b);
		} else {
			String mess = null;
			if (b == null) {
				mess = "传入参数不能为 null";
			} else {
				mess = "必须提供board_id作为查询的条件";
			}
			throw new IllegalArgumentException(mess);
		}
	}

	/**
	 * 更新版块帖子数(自增)
	 * 
	 * @param b
	 *            版块ID
	 * 
	 * @return 1成功,-1失败
	 */
	public int updateBoardNumIncrement(int id) {
		return this.updateBoardNumIncrement(new Board().setBoard_id(id));
	}

	/**
	 * 删除版块方法
	 * 
	 * @param b
	 *            版块实体
	 * @return 1成功,-1失败
	 */
	public int delete(Board b) {
		if (b != null && b.getBoard_id() != 0) {
			return sqlsession.getMapper(BoardMapper.class).delete(b);
		} else {
			String mess = null;
			if (b == null) {
				mess = "传入参数不能为 null";
			} else {
				mess = "必须提供board_id作为查询的条件";
			}
			throw new IllegalArgumentException(mess);
		}
	}

	/**
	 * 删除版块方法
	 * 
	 * @param id
	 *            版块id
	 * @return 1成功,-1失败
	 */
	public int delete(int id) {
		return this.delete(new Board().setBoard_id(id));
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
	public List<Board> find(Page page, Map<String, Object> condition,
			Map<String, OrderBy> orderBy) {
		if (page != null) {
			page.update(this.getCount(condition));
		}
		return sqlsession.getMapper(BoardMapper.class).find(page, condition,
				orderBy);
	}

	public List<Board> findAll() {
		return this.find(null, null, null);
	}

	public SqlSessionTemplate getSqlsession() {
		return sqlsession;
	}

	@Resource(name = "mybatisSession")
	public void setSqlsession(SqlSessionTemplate sqlsession) {
		this.sqlsession = sqlsession;
	}

}
