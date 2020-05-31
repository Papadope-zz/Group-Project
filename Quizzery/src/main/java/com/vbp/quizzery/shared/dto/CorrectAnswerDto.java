package com.vbp.quizzery.shared.dto;

public class CorrectAnswerDto {

	private String questionId;
	private String answerId;
	
	
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	@Override
	public String toString() {
		return "CorrectAnswerDto [questionId=" + questionId + ", answerId=" + answerId + "]";
	}
	
	
	
	
	
}
