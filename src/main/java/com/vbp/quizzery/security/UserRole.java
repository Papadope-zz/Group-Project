package com.vbp.quizzery.security;

import java.util.Set;

import com.google.common.collect.Sets;

public enum UserRole {

	PREMIUM(Sets.newHashSet(UserPermission.QUIZZ_CREATE, UserPermission.QUIZZ_TAKE)),
	SIMPLE(Sets.newHashSet(UserPermission.QUIZZ_TAKE));

	private final Set<UserPermission> permissions;

	private UserRole(Set<UserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<UserPermission> getPermissions() {
		return permissions;
	}

}
