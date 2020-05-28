package com.vbp.quizzery.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vbp.quizzery.exception.UserServiceException;
import com.vbp.quizzery.io.entity.AnswerEntity;
import com.vbp.quizzery.io.entity.QuestionEntity;
import com.vbp.quizzery.io.repositories.AnswerRepository;
import com.vbp.quizzery.io.repositories.QuestionRepository;
import com.vbp.quizzery.service.AnswerService;
import com.vbp.quizzery.shared.Utils;
import com.vbp.quizzery.shared.dto.AnswerDto;
import com.vbp.quizzery.ui.model.response.ErrorMessages;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	QuestionRepository quesR;
	@Autowired
	AnswerRepository aR;
	@Autowired
	QuestionRepository qR;
	@Autowired
	Utils utils;

	@Override
	public List<AnswerDto> getAnswers(String questionId) {
		ArrayList<AnswerDto> returnValue = new ArrayList<AnswerDto>();

		QuestionEntity question = qR.findByQuestionId(questionId);
		if (question == null)
			return returnValue;
		Iterable<AnswerEntity> answers = aR.findAllByQuestion(question);
		for (AnswerEntity aE : answers) {
			returnValue.add(new ModelMapper().map(aE, AnswerDto.class));
		}
		return returnValue;
	}

	@Override
	public AnswerDto getAnswer(String answerId) {
		AnswerDto returnValue = null;

		AnswerEntity aE = aR.findByAnswerId(answerId);
		if (aE != null) {
			returnValue = new ModelMapper().map(aE, AnswerDto.class);
		}
		return returnValue;
	}

	@Override
	public AnswerDto createAnswer(String questionId, AnswerDto answer) {

		AnswerEntity newAnswer = new ModelMapper().map(answer, AnswerEntity.class);
		newAnswer.setAnswerId(utils.generateAnswerId(20));
		newAnswer.setQuestion(qR.findByQuestionId(questionId));

		AnswerEntity savedAnswer = aR.save(newAnswer);

		AnswerDto returnValue = new ModelMapper().map(savedAnswer, AnswerDto.class);

		return returnValue;
	}

	@Override
	public AnswerDto updateAnswer(String answerId, AnswerDto answerDto) {
		AnswerEntity answerEntity = aR.findByAnswerId(answerId);
		if (answerEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		answerEntity.setAnswerText(answerDto.getAnswerText());
		answerEntity.setCorrect(answerDto.getCorrect());

		AnswerEntity updatedAnswer = aR.save(answerEntity);
		AnswerDto returnValue = new ModelMapper().map(updatedAnswer, AnswerDto.class);
		return returnValue;
	}

	@Override
	public void deleteAnswer(String answerId) {
		AnswerEntity answerEntity = aR.findByAnswerId(answerId);
		if (answerEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		aR.delete(answerEntity);

	}

	@Transactional
	@Override
	public void deleteAnswersByQuestionId(String questionId) {
		QuestionEntity questionEntity = quesR.findByQuestionId(questionId);
		if (questionEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		aR.deleteByQuestion(questionEntity);

	}

}
