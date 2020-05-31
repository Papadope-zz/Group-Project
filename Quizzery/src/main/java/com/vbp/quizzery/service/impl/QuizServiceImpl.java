package com.vbp.quizzery.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.vbp.quizzery.exception.UserServiceException;
import com.vbp.quizzery.io.entity.QuizEntity;
import com.vbp.quizzery.io.entity.UserEntity;
import com.vbp.quizzery.io.repositories.QuizRepository;
import com.vbp.quizzery.io.repositories.UserRepository;
import com.vbp.quizzery.service.QuizService;
import com.vbp.quizzery.shared.Utils;
import com.vbp.quizzery.shared.dto.QuizDto;
import com.vbp.quizzery.shared.dto.UserDto;
import com.vbp.quizzery.ui.model.response.ErrorMessages;
import com.vbp.quizzery.ui.model.response.QuizRest;
import com.vbp.quizzery.ui.model.response.UserRest;


@Service
public class QuizServiceImpl implements QuizService {

	
	@Autowired
	UserRepository uR;
	@Autowired
	QuizRepository qR;
	
	
	@Autowired
	Utils utils;
	

	@Override
	public QuizDto createQuiz(String loggedUserName, QuizDto quiz) {
		
		ModelMapper mM = new ModelMapper();

		QuizEntity quizEntity=mM.map(quiz, QuizEntity.class);	
		quizEntity.setUser(uR.findByUserName(loggedUserName));
		quizEntity.setDateCreated(new Date());
		quizEntity.setQuizId(utils.generateUserId()); // random  id 20 alphanumeric characters long
		
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
			
			System.out.println(quizEntity.toString());
			
			returnValue=new ModelMapper().map(quizEntity, QuizDto.class);
		
			return returnValue;
	}

	@Override
	public QuizDto updateQuiz(String quizId, QuizDto quizDto) {
		QuizEntity quizEntity = qR.findByQuizId(quizId);
		if (quizEntity==null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		quizEntity.setCategory(quizDto.getCategory());
		quizEntity.setDescription(quizDto.getDescription());
		quizEntity.setDifficulty(quizDto.getDifficulty());
		quizEntity.setQuizName(quizDto.getQuizName());
		quizEntity.setSubject(quizDto.getSubject());
		quizEntity.setType(quizDto.getType());
	
		QuizEntity updatedQuizDetails=qR.save(quizEntity);
		QuizDto returnValue=new ModelMapper().map(updatedQuizDetails,QuizDto.class);
		return returnValue;
	}
	
	

	@Override
	public void deleteQuiz(String quizId) {
		QuizEntity quizEntity = qR.findByQuizId(quizId);	
		if (quizEntity==null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		qR.delete(quizEntity);
	}

	@Override
	public List<QuizDto> getQuizzes(String loggedUserName) {	
		List<QuizDto> returnValue = new ArrayList<>();
		UserEntity loggedUser = uR.findByUserName(loggedUserName);	
		List<QuizEntity> quizess = qR.findAllByUser(loggedUser);
		
		for (QuizEntity qE:quizess) {		
			returnValue.add(new ModelMapper().map(qE, QuizDto.class));
		}		
		return returnValue;
	}

}
