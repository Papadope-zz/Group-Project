package com.vbp.quizzery.service;

import java.util.List;

import com.vbp.quizzery.shared.dto.AnswerDto;

public interface AnswerService {

	public List<AnswerDto> getAnswers(String questionId);
	public AnswerDto getAnswer(String answerId);
	public AnswerDto createAnswer(String questionId, AnswerDto answer);
	public AnswerDto updateAnswer(String answerId, AnswerDto answer);
	public void deleteAnswer(String answerId);
}
