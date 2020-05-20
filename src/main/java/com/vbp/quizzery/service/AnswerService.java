package com.vbp.quizzery.service;

import java.util.List;

import com.vbp.quizzery.shared.dto.AnswerDto;

public interface AnswerService {

	List<AnswerDto> getAnswers(String questionId);
	AnswerDto getAnswer(String answerId);
}
