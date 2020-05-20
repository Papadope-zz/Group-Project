package com.vbp.quizzery.ui.model.response;

import java.util.List;

import com.vbp.quizzery.shared.dto.AnswerDto;
import com.vbp.quizzery.shared.dto.QuizDto;

public class QuestionRest {

	private String questionId;
	private String questionText;	
	private int points;	
	private List<AnswerRest> answers;

	
	
	
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public List<AnswerRest> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswerRest> answers) {
		this.answers = answers;
	}
	
}
