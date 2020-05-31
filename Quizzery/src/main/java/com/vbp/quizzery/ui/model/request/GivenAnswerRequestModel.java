package com.vbp.quizzery.ui.model.request;

public class GivenAnswerRequestModel {

	
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
		return "GivenAnswer [questionId=" + questionId + ", answerId=" + answerId + "]";
	}
	
	
	
	
}
