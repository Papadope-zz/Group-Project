package com.vbp.quizzery.ui.model.request;

import java.util.List;

import com.vbp.quizzery.shared.dto.AnswerDto;
import com.vbp.quizzery.shared.dto.QuizDto;

public class QuestionRequestModel {

	private String questionText;	
	private int points;	
	private List<AnswerDto> answers;
	
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
	public List<AnswerDto> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswerDto> answers) {
		this.answers = answers;
	}

}
