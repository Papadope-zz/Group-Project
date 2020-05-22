package com.vbp.quizzery.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vbp.quizzery.io.entity.AnswerEntity;
import com.vbp.quizzery.io.entity.QuestionEntity;
import com.vbp.quizzery.io.repositories.AnswerRepository;
import com.vbp.quizzery.io.repositories.QuestionRepository;
import com.vbp.quizzery.service.AnswerService;
import com.vbp.quizzery.shared.dto.AnswerDto;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	AnswerRepository aR;
	@Autowired
	QuestionRepository qR;
	
	@Override
	public List<AnswerDto> getAnswers(String questionId) {
		ArrayList<AnswerDto> returnValue= new ArrayList<AnswerDto> ();
		
		QuestionEntity question=qR.findByQuestionId(questionId);	
		if (question==null) return returnValue;	
		Iterable<AnswerEntity> answers = aR.findAllByQuestion(question);
		for(AnswerEntity aE:answers) {		
			returnValue.add(new ModelMapper().map(aE,AnswerDto.class));
		}
		return returnValue;
	}

	@Override
	public AnswerDto getAnswer(String answerId) {
		AnswerDto returnValue=null;
		
		AnswerEntity aE=aR.findByAnswerId(answerId);
		
		if (aE!=null) {
			
			returnValue=new ModelMapper().map(aE,AnswerDto.class);
		
		}
		return returnValue;
	}

}
