package com.vbp.quizzery.io.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "chat_entity")
public class ChatUserEntity {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@JoinColumn(name = "users_id", unique = true)
	private UserEntity user;

	public ChatUserEntity() {

	}

	public ChatUserEntity(UserEntity user) {

		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
