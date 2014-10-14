package com.demo.common.emun;

public enum UserLock {
	UNLOCK(1), LOCK(2);
	private int i;

	UserLock(int lock) {
		i = lock;
	}

	public int getValue() {
		return i;
	}
}
