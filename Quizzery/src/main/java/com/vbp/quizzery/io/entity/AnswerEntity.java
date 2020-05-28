package com.vbp.quizzery.io.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "answers")
public class AnswerEntity implements Serializable {

	private static final long serialVersionUID = -8748618750495177095L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = true, length = 50)
	private String answerId;

	@Column(nullable = true)
	private String answerText;

	@Column(nullable = true)
	private Boolean correct;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "question_id")
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

	@Override
	public String toString() {
		return "AnswerEntity [id=" + id + ", answerId=" + answerId + ", answerText=" + answerText + ", correct="
				+ correct + ", question=" + question + "]";
	}

}
