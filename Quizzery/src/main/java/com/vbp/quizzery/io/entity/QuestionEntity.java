package com.vbp.quizzery.io.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.vbp.quizzery.io.entity.QuestionEntity;


@Entity(name="questions")
public class QuestionEntity implements Serializable{

	
	private static final long serialVersionUID = 2864299880693121907L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false, length = 50)
	private String questionId;

	@Column(nullable = false)
	private String questionText;
	
	private int points;
	
	@OneToMany(mappedBy="question", cascade=CascadeType.ALL)
	private List<AnswerEntity> answers;
	
	@ManyToOne
	@JoinColumn(name="quiz_id")
	private QuizEntity quiz;


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

	public List<AnswerEntity> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerEntity> answers) {
		this.answers = answers;
	}

	public QuizEntity getQuiz() {
		return quiz;
	}

	public void setQuiz(QuizEntity quiz) {
		this.quiz = quiz;
	}
	
	
}
