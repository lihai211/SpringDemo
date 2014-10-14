package com.demo.service;

import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.common.PointsSystem;
import com.demo.common.emun.UserLock;
import com.demo.common.emun.UserType;
import com.demo.dao.LoginLogDao;
import com.demo.dao.UserDao;
import com.demo.entities.User;

@Service(value = "UserService")
public class UserService {
	private UserDao userDao;
	private LoginLogDao logDao;

	public int regist(User user) {
		if (userDao.get(user) != null) {
			return -1;
		}
		try {
			user.setLocked(UserLock.UNLOCK.getValue())
					.setUser_type(UserType.NORMAL_USER.getValue()).setCredit(0);
			return userDao.add(user);
		} catch (Exception e) {
			return -1;
		}
	}

	public boolean checkUserName(String username) {
		return userDao.getByUserName(username) != null ? false : true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public int login(User user) {
		String ip = user.getLoginLog().getIp();
		user = userDao.get(user);
		if (user != null) {
			if (user.getLocked() == UserLock.UNLOCK.getValue()) {
				Calendar c1 = Calendar.getInstance();
				Calendar c2 = Calendar.getInstance();
				c2.setTime(user.getLoginLog().getLogin_datetime());
				if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR)
						|| c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH)
						|| c1.get(Calendar.DATE) != c2.get(Calendar.DATE)) {
					this.userDao.IncreaseCredit(user.getUser_id(),
							PointsSystem.get("point.login"));
				}
				user = this.userDao.getByID(user.getUser_id());
				this.logDao.add(user.getUser_id(), ip);
			} else {
				return 2;
			}
		}
		return 1;
	}

	@Resource(name = "UserDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Resource(name = "LoginLogDao")
	public void setLogDao(LoginLogDao logDao) {
		this.logDao = logDao;
	}

}
