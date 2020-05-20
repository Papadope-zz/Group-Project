package com.vbp.quizzery.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.vbp.quizzery.shared.dto.QuizDto;


public interface QuizService {

	QuizDto createQuiz(QuizDto quiz);
	QuizDto getQuizByQuizId(String userId);
	QuizDto updateQuiz(String quizId, QuizDto quizDto);
	void deleteQuiz(String quizId);
	public List<QuizDto> getQuizzes(int page, int limit);
	
	
}
