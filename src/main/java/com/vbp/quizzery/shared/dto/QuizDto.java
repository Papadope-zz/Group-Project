package com.vbp.quizzery.shared.dto;

import java.util.Date;
import java.util.List;


public class QuizDto {
	
	private long id;	
	private String quizId;
	private String quizName;
	private String description;
	private String subject;
	private String category;	
	private String difficulty;	
	private String type;
	private Date dateCreated;	
 	private List<QuestionDto> questions;
	private UserDto user;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public List<QuestionDto> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionDto> questions) {
		this.questions = questions;
	}

	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	
	
	@Override
	public String toString() {
		return "QuizDto [id=" + id + ", quizId=" + quizId + ", quizName=" + quizName + ", description=" + description
				+ ", subject=" + subject + ", category=" + category + ", difficulty=" + difficulty + ", type=" + type
				+ ", dateCreated=" + dateCreated /*+ ", questions=" + questions */+ "]";
	}
	
	public void printQuiz() {
		
		System.out.println("id=" + id + "\nquizId=" + quizId + "\nquizName=" + quizName + "\ndescription=" + description
				+ "\nsubject=" + subject + "\ncategory=" + category + "\ndifficulty=" + difficulty + "\ntype=" + type
				+ "\ndateCreated=" + dateCreated);
		
	}
	
	
	
}
