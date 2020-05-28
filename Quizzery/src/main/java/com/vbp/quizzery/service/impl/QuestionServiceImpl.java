package com.vbp.quizzery.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vbp.quizzery.exception.UserServiceException;
import com.vbp.quizzery.io.entity.AnswerEntity;
import com.vbp.quizzery.io.entity.QuestionEntity;
import com.vbp.quizzery.io.entity.QuizEntity;
import com.vbp.quizzery.io.repositories.AnswerRepository;
import com.vbp.quizzery.io.repositories.QuestionRepository;
import com.vbp.quizzery.io.repositories.QuizRepository;
import com.vbp.quizzery.service.AnswerService;
import com.vbp.quizzery.service.QuestionService;
import com.vbp.quizzery.shared.Utils;
import com.vbp.quizzery.shared.dto.AnswerDto;
import com.vbp.quizzery.shared.dto.QuestionDto;
import com.vbp.quizzery.ui.model.response.ErrorMessages;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	AnswerRepository aR;
	@Autowired
	QuestionRepository quesR;
	@Autowired
	QuizRepository quizR;
	@Autowired
	AnswerService aS;

	@Autowired
	Utils utils;

	@Override
	public List<QuestionDto> getQuestions(String quizId) {

		ArrayList<QuestionDto> returnValue = new ArrayList<QuestionDto>();

		QuizEntity quiz = quizR.findByQuizId(quizId);
		if (quiz == null)
			return returnValue;
		Iterable<QuestionEntity> questions = quesR.findAllByQuiz(quiz);
		for (QuestionEntity qE : questions) {
			returnValue.add(new ModelMapper().map(qE, QuestionDto.class));
		}
		return returnValue;
	}

	@Override
	public QuestionDto getQuestion(String questionId) {

		QuestionDto returnValue = null;
		QuestionEntity qE = quesR.findByQuestionId(questionId);
		if (qE != null) {

			returnValue = new ModelMapper().map(qE, QuestionDto.class);
		}
		return returnValue;

	}

	@Override
	public QuestionDto createQuestion(String quizId, QuestionDto question) {
		ModelMapper mM = new ModelMapper();

		QuestionEntity questionEntity = mM.map(question, QuestionEntity.class);
		questionEntity.setQuestionId(utils.generateQuestionId(20)); // random id 20 alphanumeric characters long
		questionEntity.setQuiz(quizR.findByQuizId(quizId));
		for (AnswerEntity aE : questionEntity.getAnswers()) {
			aE.setAnswerId(utils.generateAnswerId(20));
			aE.setQuestion(questionEntity);

		}

		QuestionEntity storedQuestionDetails = quesR.save(questionEntity);
		QuestionDto returnValue = mM.map(storedQuestionDetails, QuestionDto.class);
		return returnValue;
	}

	@Override
	public QuestionDto updateQuestion(String questionId, QuestionDto question) {

		ModelMapper mm = new ModelMapper();

		// aR.deleteByQuestion(questionEntity);

		QuestionEntity questionEntity = quesR.findByQuestionId(questionId);
		if (questionEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		aS.deleteAnswersByQuestionId(questionId);

		List<AnswerEntity> answerEntityList = new ArrayList<>();
		List<AnswerDto> answerDtoList = question.getAnswers();

		questionEntity.setQuestionText(question.getQuestionText());

		for (AnswerDto answerDto : answerDtoList) {
			answerDto.setAnswerId(utils.generateAnswerId(20));

			AnswerEntity aE = (mm.map(answerDto, AnswerEntity.class));
			aE.setQuestion(questionEntity);

			answerEntityList.add(aE);
		}

		questionEntity.setAnswers(answerEntityList);

		QuestionEntity updatedQuestionDetails = quesR.save(questionEntity);
		QuestionDto returnValue = mm.map(updatedQuestionDetails, QuestionDto.class);
		return returnValue;
	}

	@Override
	public void deleteQuestion(String questionId) {
		QuestionEntity questionEntity = quesR.findByQuestionId(questionId);
		if (questionEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		quesR.delete(questionEntity);
	}

}
