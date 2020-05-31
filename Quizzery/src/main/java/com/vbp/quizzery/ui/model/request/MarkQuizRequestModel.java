package com.vbp.quizzery.ui.model.request;

import java.util.List;

public class MarkQuizRequestModel {

	private String quizId;
	private List<GivenAnswerRequestModel> correctAnswers;
	
	public String getQuizId() {
		return quizId;
	}
	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}
	
	public List<GivenAnswerRequestModel> getCorrectAnswers() {
		return correctAnswers;
	}
	public void setCorrectAnswers(List<GivenAnswerRequestModel> correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
	@Override
	public String toString() {
		return "MarkQuizRequest [quizId=" + quizId + ", correctAnswers=" + correctAnswers + "]";
	}
			
}
