package com.vbp.quizzery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vbp.quizzery.io.entity.AnswerEntity;
import com.vbp.quizzery.io.entity.QuestionEntity;
import com.vbp.quizzery.io.entity.QuizEntity;
import com.vbp.quizzery.io.repositories.QuizRepository;
import com.vbp.quizzery.service.ResultsService;
import com.vbp.quizzery.shared.dto.CorrectAnswerDto;

import com.vbp.quizzery.shared.dto.QuizToMarkDto;
import com.vbp.quizzery.ui.model.response.MarkedQuiz;

@Service
public class ResultsServiceImpl implements ResultsService{

	@Autowired
	QuizRepository qR;
	
	@Override
	public MarkedQuiz markQuiz(QuizToMarkDto quizToMarkDto) {
	
		QuizEntity quiz=qR.findByQuizId(quizToMarkDto.getQuizId());
		Float totalMark=0f;
		Float mark=0f;
		
		int correctAnswers=0;
		
		for (QuestionEntity q:quiz.getQuestions()) {
			totalMark+=q.getPoints();
			for (AnswerEntity a:q.getAnswers()) {
				
				for (CorrectAnswerDto ca:quizToMarkDto.getCorrectAnswers()) {			
					if (a.getAnswerId().equals(ca.getAnswerId()) && a.getCorrect())
					{mark+=q.getPoints(); correctAnswers++;}	
				}	
			}			
		}
		
		MarkedQuiz returnValue=new MarkedQuiz(correctAnswers, quiz.getQuestions().size(), mark, totalMark);
		
		
		return returnValue;			
	}
		
	
	
	

}
