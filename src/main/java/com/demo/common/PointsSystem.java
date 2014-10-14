package com.demo.common;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public final class PointsSystem {
	private static Properties pro;

	public static void init() {
		pro = new Properties();
		try {
			pro.load(new ClassPathResource("pointsSystem.properties")
					.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int get(String name) {
		return Integer.parseInt(pro.get(name).toString().trim());
	}
}
