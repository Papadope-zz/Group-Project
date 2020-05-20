package com.vbp.quizzery.security;

public enum UserPermission {

	QUIZZ_CREATE("quizz:create"), QUIZZ_TAKE("quizz:take");

	private final String permission;

	private UserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}

}
