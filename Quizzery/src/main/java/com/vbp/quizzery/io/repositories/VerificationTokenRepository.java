package com.vbp.quizzery.io.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vbp.quizzery.io.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	VerificationToken findByToken(String token);
}
