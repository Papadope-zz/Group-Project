package com.vbp.quizzery.io.repositories;

import org.springframework.data.repository.CrudRepository;

import com.vbp.quizzery.io.entity.ChatUserEntity;
import com.vbp.quizzery.io.entity.UserEntity;

public interface ChatUserRepository extends CrudRepository<ChatUserEntity, Long> {

	public Long deleteByUser(UserEntity user);
}
