package com.vbp.quizzery.ui.model.request;


import java.util.List;

import com.vbp.quizzery.ui.model.response.QuestionRest;

public class QuizDetailsRequestModel {

	private String quizName;
	private String description;
	private String subject;
	private String category;	
	private String difficulty;	
	private String type;
	private List<QuestionRest> questions;
	
	
	public String getQuizName() {
		return quizName;
	}
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<QuestionRest> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionRest> questions) {
		this.questions = questions;
	}
	
	
	
}
