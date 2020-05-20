package com.vbp.quizzery.io.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name="answers")
public class AnswerEntity implements Serializable {


	private static final long serialVersionUID = -8748618750495177095L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false, length = 50)
	private String answerId;

	@Column(nullable = false)
	private String answerText;
		
	@Column(nullable = false)
	private Boolean correct;
	
	@ManyToOne
	@JoinColumn(name="question_id")
	private QuestionEntity question;

	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public QuestionEntity getQuestion() {
		return question;
	}

	public void setQuestion(QuestionEntity question) {
		this.question = question;
	}
	
	
	
}
