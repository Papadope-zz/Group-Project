package com.vbp.quizzery.ui.model.response;

import java.util.Date;
import java.util.List;

import com.vbp.quizzery.shared.dto.QuestionDto;

public class QuizRest {

	
	
	private String quizId;
	private String quizName;
	private String description;
	private String subject;
	private String category;	
	private String difficulty;	
	private String type;
	private Date dateCreated;	
	private List<QuestionRest> questions;
	
	
	public String getQuizId() {
		return quizId;
	}
	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}
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
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public List<QuestionRest> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionRest> questions) {
		this.questions = questions;
	}
	
	@Override
	public String toString() {
		return "QuizRest [quizId=" + quizId + ", quizName=" + quizName + ", description=" + description + ", subject="
				+ subject + ", category=" + category + ", difficulty=" + difficulty + ", type=" + type
				+ ", dateCreated=" + dateCreated + ", questions=" + questions + "]";
	}
	
}
