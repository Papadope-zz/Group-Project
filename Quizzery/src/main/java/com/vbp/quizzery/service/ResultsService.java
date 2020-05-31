package com.vbp.quizzery.service;


import com.vbp.quizzery.shared.dto.QuizToMarkDto;
import com.vbp.quizzery.ui.model.response.MarkedQuiz;

public interface ResultsService {
	
	public MarkedQuiz markQuiz(QuizToMarkDto quizToMarkDto);	
	
}
