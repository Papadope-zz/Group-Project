package com.vbp.quizzery.ui.model.response;

import com.vbp.quizzery.shared.dto.QuestionDto;

public class AnswerRest {

	
	private String answerId;
	private String answerText;
	private Boolean correct;

	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
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
