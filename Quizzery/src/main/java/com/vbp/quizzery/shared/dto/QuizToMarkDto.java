package com.vbp.quizzery.shared.dto;

import java.util.List;

import com.vbp.quizzery.ui.model.request.GivenAnswerRequestModel;

public class QuizToMarkDto {
	private String quizId;
	private List<CorrectAnswerDto> correctAnswers;
	public String getQuizId() {
		return quizId;
	}
	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}
	public List<CorrectAnswerDto> getCorrectAnswers() {
		return correctAnswers;
	}
	public void setCorrectAnswers(List<CorrectAnswerDto> correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
	@Override
	public String toString() {
		return "QuizToMarkDto [quizId=" + quizId + ", correctAnswers=" + correctAnswers + "]";
	}

			
}
