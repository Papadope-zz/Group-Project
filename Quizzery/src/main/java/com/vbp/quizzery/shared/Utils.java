package com.vbp.quizzery.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	private final int USER_ID_LENGTH=30;
	private final int QUIZ_ID_LENGTH=20;
	private final int QUESTION_ID_LENGTH=20;
	private final int ANSWER_ID_LENGTH=20;
	
	public String generateUserId() {
		return generateRandomString(USER_ID_LENGTH);
	}

	public String generateQuizId() {
		return generateRandomString(QUIZ_ID_LENGTH);
	}

	public String generateQuestionId() {
		return generateRandomString(QUESTION_ID_LENGTH);
	}

	public String generateAnswerId() {
		return generateRandomString(ANSWER_ID_LENGTH);
	}


	private String generateRandomString(int length) {

		StringBuilder returnValue = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));

		}
		return new String(returnValue);

	}

}
