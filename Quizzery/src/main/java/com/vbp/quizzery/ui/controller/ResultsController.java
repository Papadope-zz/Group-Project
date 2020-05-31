package com.vbp.quizzery.ui.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vbp.quizzery.service.impl.AnswerServiceImpl;
import com.vbp.quizzery.service.impl.QuestionServiceImpl;
import com.vbp.quizzery.service.impl.ResultsServiceImpl;

import com.vbp.quizzery.shared.dto.QuizToMarkDto;
import com.vbp.quizzery.ui.model.request.MarkQuizRequestModel;

import com.vbp.quizzery.ui.model.response.MarkedQuiz;



@RestController
@RequestMapping("/results")
public class ResultsController {

	@Autowired
	ResultsServiceImpl rS;
	@Autowired
	QuestionServiceImpl qS;
	@Autowired
	AnswerServiceImpl aS;
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, // MediaType.APPLICATION_XML_VALUE,
			produces = { MediaType.APPLICATION_JSON_VALUE }) // MediaType.APPLICATION_XML_VALUE,

	public MarkedQuiz getQuizMark(@RequestBody MarkQuizRequestModel markQuizRequestModel)
			throws Exception {

		System.out.println(markQuizRequestModel.toString());
		
		if (markQuizRequestModel.getCorrectAnswers().isEmpty()||markQuizRequestModel.getCorrectAnswers()==null)
			throw new NullPointerException("The Object is null");

		QuizToMarkDto quizToMarkDto = new ModelMapper().map(markQuizRequestModel, QuizToMarkDto.class);
		
		System.out.println(quizToMarkDto.toString());

		MarkedQuiz returnValue=rS.markQuiz(quizToMarkDto);
		
		System.out.println(returnValue);
		
		return returnValue;
	}
	
	
}
