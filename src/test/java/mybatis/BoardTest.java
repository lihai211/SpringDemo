package mybatis;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.dao.BoardDao;

public class BoardTest extends TestCase {
	private static ApplicationContext ac = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private static BoardDao bd = ac.getBean(BoardDao.class);

	public static void main(String[] args) {
	}

	private static CyclicBarrier cb = new CyclicBarrier(1000);

	private static void testUpdateNum() {
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						cb.await();
						System.out.println(Thread.currentThread().getName()
								+ " " + bd.updateBoardNumIncrement(1)
								+ " finish");
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			}, "thread" + i).start();
		}
	}
}
