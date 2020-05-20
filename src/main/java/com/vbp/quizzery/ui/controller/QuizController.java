package com.vbp.quizzery.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.vbp.quizzery.service.QuestionService;
import com.vbp.quizzery.service.QuizService;
import com.vbp.quizzery.service.UserService;
import com.vbp.quizzery.shared.dto.QuestionDto;
import com.vbp.quizzery.shared.dto.QuizDto;
import com.vbp.quizzery.ui.model.request.QuizDetailsRequestModel;
import com.vbp.quizzery.ui.model.response.OperationStatusModel;
import com.vbp.quizzery.ui.model.response.QuestionRest;
import com.vbp.quizzery.ui.model.response.QuizRest;
import com.vbp.quizzery.ui.model.response.RequestOperationNames;
import com.vbp.quizzery.ui.model.response.RequestOperationStatus;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

	@Autowired
	UserService uS;
	@Autowired
	QuizService qS;
	@Autowired
	QuestionService queS;
	
	@GetMapping(path="/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public QuizRest getQuiz(@PathVariable String id) {
		System.out.println("received");		
//		UserRest returnValue = new UserRest();
		QuizDto quizDto = new QuizDto();
		quizDto=qS.getQuizByQuizId(id);
		
		ModelMapper mM = new ModelMapper();
		QuizRest returnValue = mM.map(quizDto,QuizRest.class);
//		BeanUtils.copyProperties(quizDto, returnValue);	

		return returnValue;
	}
	
	
	
	@PostMapping(consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
				 produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	
	public QuizRest createQuiz(@RequestBody QuizDetailsRequestModel quizDetails) throws Exception {
					
		if (quizDetails.getQuizName().isEmpty()) throw new NullPointerException ("The Object is null");
		
		ModelMapper mM = new ModelMapper();
		QuizDto quizDto = mM.map(quizDetails, QuizDto.class);

		QuizDto createdQuiz = qS.createQuiz(quizDto);

		QuizRest returnValue = mM.map(createdQuiz,QuizRest.class);
		return returnValue;
	}
	
	@PutMapping(path="/{id}", 
				consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
				produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	
	public QuizRest updateQuiz(@PathVariable String id, @RequestBody QuizDetailsRequestModel quizDetails) throws Exception {
					
		ModelMapper mM = new ModelMapper();
		
		QuizDto quizDto = mM.map(quizDetails, QuizDto.class);
		
		QuizDto updatedQuiz = qS.updateQuiz(id, quizDto);
		

		QuizRest returnValue = mM.map(updatedQuiz,QuizRest.class);
		return returnValue;
	}
	
	
	
	@DeleteMapping(path="/{id}",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	
		public OperationStatusModel deleteUser(@PathVariable String id) {
	
		qS.deleteQuiz(id);

		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationNames.DELETE.name());
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		return returnValue;
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<QuizRest> getQuizzes(@RequestParam(value="page", defaultValue="0") int page, 
			@RequestParam(value="limit", defaultValue="25") int limit){
		List<QuizRest> returnValue = new ArrayList<>();
		
		List<QuizDto> quizzes = qS.getQuizzes(page,limit);
		
		for (QuizDto quizDto:quizzes ) {		
			QuizRest quizModel = new QuizRest();
			
			BeanUtils.copyProperties(quizDto, quizModel);
			
			returnValue.add(quizModel);
		}
		
		
		return returnValue;
		
	}
	//http://localhost:8080/mobile-app/users/userID/addresses
	@GetMapping(path="/{id}/questions",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<QuestionRest> getQuizQuestions(@PathVariable String id) {
		
		List<QuestionRest> returnValue = new ArrayList<>();
		List<QuestionDto> questionDto=queS.getQuestions(id);
		
		if (questionDto!=null && !questionDto.isEmpty()) {
		java.lang.reflect.Type listType = new TypeToken<List<QuestionRest>>() {}.getType();
		returnValue = new ModelMapper().map(questionDto, listType);
		}

		return returnValue;
}

	
	@GetMapping(path="/{Id}/questions/{qId}",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public QuestionRest getQuizQuestion(@PathVariable String qId) {
			
//		AddressDto addressesDto=aS.getAddress(aId);
	
		return new ModelMapper().map(queS.getQuestion(qId), QuestionRest.class);
}
	
	
}

	
	

