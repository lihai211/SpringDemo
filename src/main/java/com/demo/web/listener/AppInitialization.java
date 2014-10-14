package com.demo.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.demo.common.AppConfig;
import com.demo.common.PointsSystem;
import com.demo.common.log.Log4jBackstop;

public class AppInitialization implements ServletContextListener {
	private final Logger log = LogManager.getLogger(this.getClass());

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		log.info("~~~~~~~~~~~~~~~~~~~~~~系统正在关闭~~~~~~~~~~~~~~~~~~~~~~");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		log.warn("~~~~~~~~~~~~~~~~~~~~~~系统正在启动~~~~~~~~~~~~~~~~~~~~~~");
		init();
	}

	private void init() {
		Thread.setDefaultUncaughtExceptionHandler(new Log4jBackstop());
		AppConfig.init();
		PointsSystem.init();
	}
}
