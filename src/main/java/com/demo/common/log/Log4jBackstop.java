package com.demo.common.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jBackstop implements Thread.UncaughtExceptionHandler {
	private final Logger log = LogManager.getLogger(this.getClass());

	@Override
	public void uncaughtException(Thread arg0, Throwable arg1) {
		log.error("Exception in thread  [" + arg0.getName() + "]  : ", arg1);
	}

}
