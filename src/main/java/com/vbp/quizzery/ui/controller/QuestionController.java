package com.vbp.quizzery.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vbp.quizzery.service.AnswerService;
import com.vbp.quizzery.service.QuestionService;
import com.vbp.quizzery.service.QuizService;
import com.vbp.quizzery.service.UserService;
import com.vbp.quizzery.shared.dto.QuestionDto;
import com.vbp.quizzery.ui.model.request.QuestionRequestModel;
import com.vbp.quizzery.ui.model.response.OperationStatusModel;
import com.vbp.quizzery.ui.model.response.QuestionRest;
import com.vbp.quizzery.ui.model.response.RequestOperationNames;
import com.vbp.quizzery.ui.model.response.RequestOperationStatus;

@RestController
@RequestMapping("/quizzes/{id}/questions")
public class QuestionController {

	@Autowired
	UserService uS;
	@Autowired
	QuizService qS;
	@Autowired
	QuestionService queS;
	@Autowired
	AnswerService aS;
	
	//http://localhost:8080/mobile-app/users/userID/addresses
		@GetMapping(path="/",
				produces = {MediaType.APPLICATION_JSON_VALUE}) //MediaType.APPLICATION_XML_VALUE, 
		public List<QuestionRest> getQuizQuestions(@PathVariable String id) {
			
			List<QuestionRest> returnValue = new ArrayList<>();
			List<QuestionDto> questionDto=queS.getQuestions(id);
			
			if (questionDto!=null && !questionDto.isEmpty()) {
			java.lang.reflect.Type listType = new TypeToken<List<QuestionRest>>() {}.getType();
			returnValue = new ModelMapper().map(questionDto, listType);
			}

			return returnValue;
	}

		
		@GetMapping(path="/{qId}",
				produces = {MediaType.APPLICATION_JSON_VALUE}) //MediaType.APPLICATION_XML_VALUE, 
		public QuestionRest getQuizQuestion(@PathVariable String qId) {

			return new ModelMapper().map(queS.getQuestion(qId), QuestionRest.class);
	}
		
		
		@PostMapping(path="/",
				consumes={MediaType.APPLICATION_JSON_VALUE}, //MediaType.APPLICATION_XML_VALUE, 
				produces = {MediaType.APPLICATION_JSON_VALUE})//MediaType.APPLICATION_XML_VALUE,
		
		public QuestionRest createQuestion(@RequestBody QuestionRequestModel questionDetails, @PathVariable String id) throws Exception {

			if (questionDetails.getQuestionText().isEmpty()) throw new NullPointerException ("The Object is null");
			
			ModelMapper mM = new ModelMapper();
			
			QuestionDto questionDto = mM.map(questionDetails, QuestionDto.class);

			QuestionDto createdQuestion = queS.createQuestion(id, questionDto);

			QuestionRest returnValue = mM.map(createdQuestion,QuestionRest.class);
			return returnValue;
		}
		 
		@PutMapping(path="/{qId}", 
				consumes = {MediaType.APPLICATION_JSON_VALUE}, 
				produces = {MediaType.APPLICATION_JSON_VALUE} ) //MediaType.APPLICATION_XML_VALUE,
		
		public QuestionRest updateQuestion(@PathVariable String qId, @RequestBody QuestionRequestModel questionDetails) throws Exception {
						
			ModelMapper mM = new ModelMapper();
			
			QuestionDto questionDto = mM.map(questionDetails, QuestionDto.class);	
			QuestionDto updatedQuestion = queS.updateQuestion(qId, questionDto);
			QuestionRest returnValue = mM.map(updatedQuestion,QuestionRest.class);
			
			return returnValue;
		}
		
		
		@DeleteMapping(path="/{qId}",
				produces = {MediaType.APPLICATION_JSON_VALUE}) //MediaType.APPLICATION_XML_VALUE, 
		
			public OperationStatusModel deleteQuestion(@PathVariable String qId) {
		
			queS.deleteQuestion(qId);

			OperationStatusModel returnValue = new OperationStatusModel();
			returnValue.setOperationName(RequestOperationNames.DELETE.name());
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
			
			return returnValue;
		}
		
		
	
	
	
	
	
}
