package com.vbp.quizzery.ui.model.response;

public class MarkedQuiz {
	
	private Integer correctAnswers;
	private Integer totalAnswers;
	
	private Float fullMark;
	private Float givenMark;
	
	public MarkedQuiz() {};
	
	public MarkedQuiz(Integer correctAnswers, Integer totalAnswers,  Float givenMark, Float fullMark) {
		super();
		this.correctAnswers = correctAnswers;
		this.totalAnswers = totalAnswers;
		this.fullMark = fullMark;
		this.givenMark = givenMark;	
	}
	
	
	
	public Integer getCorrectAnswers() {
		return correctAnswers;
	}
	public void setCorrectAnswers(Integer correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
	public Float getGivenMark() {
		return givenMark;
	}
	public void setGivenMark(Float givenMark) {
		this.givenMark = givenMark;
	}
	public Integer getTotalAnswers() {
		return totalAnswers;
	}
	public void setTotalAnswers(Integer totalAnswers) {
		this.totalAnswers = totalAnswers;
	}
	public Float getFullMark() {
		return fullMark;
	}
	public void setFullMark(Float fullMark) {
		this.fullMark = fullMark;
	}

	@Override
	public String toString() {
		return "MarkedQuiz [correctAnswers=" + correctAnswers + ", totalAnswers=" + totalAnswers + ", fullMark="
				+ fullMark + ", givenMark=" + givenMark + "]";
	}
	
	

}
