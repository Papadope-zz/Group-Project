package com.vbp.quizzery.io.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name="quizzes")
public class QuizEntity implements Serializable{

	
	private static final long serialVersionUID = -6262452663430713733L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = true, length = 50)
	private String quizId;

	@Column(nullable = true, length = 50)
	private String quizName;
	
	@Column(nullable = true, length = 120)
	private String description;
	
	@Column(nullable = true, length = 50)
	private String subject;
	
	@Column(nullable = true, length = 50)
	private String category;
	
	@Column(nullable = true, length = 50)
	private String difficulty;
	
	@Column(nullable = true, length = 50)
	private String type;
	
	@Column(nullable = true, length = 50)
	private Date dateCreated;
	
	@OneToMany(mappedBy="quiz", cascade=CascadeType.ALL)
	private List<QuestionEntity> questions;

	@ManyToOne
	@JoinColumn(name="users_id")
	private UserEntity user;
	
	
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

	public List<QuestionEntity> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionEntity> questions) {
		this.questions = questions;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "QuizEntity [id=" + id + ", quizId=" + quizId + ", quizName=" + quizName + ", description=" + description
				+ ", subject=" + subject + ", category=" + category + ", difficulty=" + difficulty + ", type=" + type
				+ ", dateCreated=" + dateCreated + ", questions=" + questions + ", user=" + user + "]";
	}
	
	
	
	
}
