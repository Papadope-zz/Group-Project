package com.vbp.quizzery.ui.model.response;

import com.vbp.quizzery.security.UserRole;

// response back to user , UI layer

public class UserRest {

	private String userId;// public user id ,not database id
	private String userName;

	private String email;
	private UserRole userRole;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "UserRest [userId=" + userId + ", userName=" + userName + ", email=" + email + ", userRole=" + userRole
				+ "]";
	}

}
