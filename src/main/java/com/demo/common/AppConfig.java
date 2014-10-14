package com.demo.common;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class AppConfig {
	private static Properties pro;

	public static void init() {
		pro = new Properties();
		try {
			pro.load(new ClassPathResource("appConfig.properties")
					.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get(String name) {
		return pro.get(name).toString().trim();
	}
}
