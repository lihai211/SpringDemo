package com.demo.common.emun;

public enum UserType {
	NORMAL_USER(1), FORUM_ADMIN(2), SUPER_ADMIN(99);
	private int i;

	UserType(int type) {
		i = type;
	}

	public int getValue() {
		return i;
	}

	public static void main(String[] args) {
		System.out.println(UserType.FORUM_ADMIN.getValue());
	}
}
