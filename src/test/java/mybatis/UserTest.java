package mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.common.emun.OrderBy;
import com.demo.common.log.Log4jBackstop;
import com.demo.dao.UserDao;
import com.demo.entities.Page;
import com.demo.entities.User;
import com.demo.service.UserService;

public class UserTest extends TestCase {
	private static ApplicationContext ac = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private static UserDao ud = ac.getBean(UserDao.class);

	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new Log4jBackstop());
		testAdd();
	}

	private static void testFind() {
		User us = new User();
		us.setUser_id(1);
		us = ud.get(us);
		System.out.println(us.toJson());
	}

	private static void testAdd() {
		User us = new User();
		us.setUser_name("test205");
		us.setPassword("C4CA4238A0B923820DCC509A6F75849B");
		UserService u = ac.getBean(UserService.class);
		System.out.println(u.regist(us));
		System.out.println(us.getUser_id());
	}

	private static void testGetCount() {
		System.out.println(ud.getCount());
	}

	private static void testPage() {
		Page page = new Page().setCurPage(1);
		Map<String, OrderBy> order = new HashMap<String, OrderBy>();
		order.put("user_id", OrderBy.asc);
		Map<String, Object> sql = new HashMap<String, Object>();
		List<User> list = ud.find(page, sql, order);
		System.out.println(list.size());
		for (User us : list) {
			System.out.println(us.toJson());
		}
	}
}
