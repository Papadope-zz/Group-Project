package com.vbp.quizzery.service;

import java.util.List;

import com.vbp.quizzery.shared.dto.QuestionDto;

public interface QuestionService {

	List<QuestionDto> getQuestions(String quizId);
	QuestionDto getQuestion(String questionId);
	
}
