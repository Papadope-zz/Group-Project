package com.vbp.quizzery.shared.dto;

import java.util.List;


public class QuestionDto {

	private long id;
	private String questionId;
	private String questionText;	
	private int points;	
	private List<AnswerDto> answers;
	private QuizDto quiz;
	
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public List<AnswerDto> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswerDto> answers) {
		this.answers = answers;
	}
	public QuizDto getQuiz() {
		return quiz;
	}
	public void setQuiz(QuizDto quiz) {
		this.quiz = quiz;
	}
	
	
	
	
	
}
