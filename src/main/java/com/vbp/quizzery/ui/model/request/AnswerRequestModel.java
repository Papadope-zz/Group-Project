package com.vbp.quizzery.ui.model.request;

public class AnswerRequestModel {

	private String answerText;
	private Boolean correct;


	public String getAnswerText() {
		return answerText;
	}
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	public Boolean getCorrect() {
		return correct;
	}
	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

}
