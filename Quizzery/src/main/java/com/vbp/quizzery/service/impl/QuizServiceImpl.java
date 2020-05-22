package com.vbp.quizzery.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vbp.quizzery.exception.UserServiceException;
import com.vbp.quizzery.io.entity.QuizEntity;
import com.vbp.quizzery.io.repositories.QuizRepository;
import com.vbp.quizzery.io.repositories.UserRepository;
import com.vbp.quizzery.service.QuizService;
import com.vbp.quizzery.shared.Utils;
import com.vbp.quizzery.shared.dto.QuizDto;
import com.vbp.quizzery.ui.model.response.ErrorMessages;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	UserRepository uR;
	@Autowired
	QuizRepository qR;

	@Autowired
	Utils utils;

	@Override
	public QuizDto createQuiz(QuizDto quiz) {

		ModelMapper mM = new ModelMapper();
		QuizEntity quizEntity = mM.map(quiz, QuizEntity.class);
		quizEntity.setQuizId(utils.generateUserId(30)); // random user id 30 alphanumeric characters long
		QuizEntity storedQuizDetails = qR.save(quizEntity);
		QuizDto returnValue = mM.map(storedQuizDetails, QuizDto.class);

		return returnValue;
	}

	@Override
	public QuizDto getQuizByQuizId(String quizId) {
		QuizDto returnValue = new QuizDto();
		QuizEntity quizEntity = qR.findByQuizId(quizId);
		if (quizEntity == null)
			throw new UsernameNotFoundException(quizId);
		BeanUtils.copyProperties(quizEntity, returnValue);
		return returnValue;
	}

	@Override
	public QuizDto updateQuiz(String quizId, QuizDto quizDto) {
		QuizEntity quizEntity = qR.findByQuizId(quizId);
		if (quizEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		BeanUtils.copyProperties(quizDto, quizEntity);

		QuizEntity updatedQuizDetails = qR.save(quizEntity);
		QuizDto returnValue = new QuizDto();
		BeanUtils.copyProperties(updatedQuizDetails, returnValue);

		return returnValue;
	}

	@Override
	public void deleteQuiz(String quizId) {
		QuizEntity quizEntity = qR.findByQuizId(quizId);
		if (quizEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		qR.delete(quizEntity);

	}

	@Override
	public List<QuizDto> getQuizzes(int page, int limit) {
		List<QuizDto> returnValue = new ArrayList<>();

		if (page > 0)
			page--;

		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<QuizEntity> usersPage = qR.findAll(pageableRequest);

		for (QuizEntity quizEntity : usersPage) {
			QuizDto quizDto = new QuizDto();
			BeanUtils.copyProperties(quizEntity, quizDto);
			returnValue.add(quizDto);
		}

		return returnValue;
	}

	@Override
	public List<QuizDto> getQuizzesByUserEmail(String email) {

		List<QuizDto> returnValue = new ArrayList<>();

		List<QuizEntity> usersPage = qR.getQuizzesByUserEmail(email);

		for (QuizEntity quizEntity : usersPage) {
			QuizDto quizDto = new QuizDto();
			BeanUtils.copyProperties(quizEntity, quizDto);
			returnValue.add(quizDto);
		}

		return returnValue;

	}

}
