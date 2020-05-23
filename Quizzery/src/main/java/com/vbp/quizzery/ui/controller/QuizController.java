package com.vbp.quizzery.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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
import com.vbp.quizzery.shared.dto.AnswerDto;
import com.vbp.quizzery.shared.dto.QuestionDto;
import com.vbp.quizzery.shared.dto.QuizDto;
import com.vbp.quizzery.shared.dto.UserDto;
import com.vbp.quizzery.ui.model.request.AnswerRequestModel;
import com.vbp.quizzery.ui.model.request.QuestionRequestModel;
import com.vbp.quizzery.ui.model.request.QuizDetailsRequestModel;
import com.vbp.quizzery.ui.model.request.UserDetailsRequestModel;
import com.vbp.quizzery.ui.model.response.AnswerRest;
import com.vbp.quizzery.ui.model.response.OperationStatusModel;
import com.vbp.quizzery.ui.model.response.QuestionRest;
import com.vbp.quizzery.ui.model.response.QuizRest;
import com.vbp.quizzery.ui.model.response.RequestOperationNames;
import com.vbp.quizzery.ui.model.response.RequestOperationStatus;
import com.vbp.quizzery.ui.model.response.UserRest;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

	@Autowired
	UserService uS;
	@Autowired
	QuizService qS;
	@Autowired
	QuestionService queS;
	@Autowired
	AnswerService aS;
	
	@GetMapping(path="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}) //MediaType.APPLICATION_XML_VALUE
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


	@PostMapping(
			consumes={MediaType.APPLICATION_JSON_VALUE}, //MediaType.APPLICATION_XML_VALUE, 
			produces = {MediaType.APPLICATION_JSON_VALUE})//MediaType.APPLICATION_XML_VALUE,
	
	public QuizRest createQuiz(@RequestBody QuizDetailsRequestModel quizDetails, @AuthenticationPrincipal User user) throws Exception {
		
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//System.out.println(auth.getPrincipal());
		
		String loggedUserName=user.getUsername();
	
		if (quizDetails.getQuizName().isEmpty()) throw new NullPointerException ("The Object is null");
		
		ModelMapper mM = new ModelMapper();
		
		
		QuizDto quizDto = mM.map(quizDetails, QuizDto.class);

		QuizDto createdQuiz = qS.createQuiz(loggedUserName, quizDto);

		QuizRest returnValue = mM.map(createdQuiz,QuizRest.class);
		return returnValue;
	}
	
	@PutMapping(path="/{id}", 
			consumes={MediaType.APPLICATION_JSON_VALUE}, //MediaType.APPLICATION_XML_VALUE, 
			produces = {MediaType.APPLICATION_JSON_VALUE})//MediaType.APPLICATION_XML_VALUE,
	
	public QuizRest updateQuiz(@PathVariable String id, @RequestBody QuizDetailsRequestModel quizDetails) throws Exception {
					
		ModelMapper mM = new ModelMapper();
		
		QuizDto quizDto = mM.map(quizDetails, QuizDto.class);
		
		QuizDto updatedQuiz = qS.updateQuiz(id, quizDto);
		

		QuizRest returnValue = mM.map(updatedQuiz,QuizRest.class);
		return returnValue;
	}


	@DeleteMapping(path="/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE}) //MediaType.APPLICATION_XML_VALUE, 
	
		public OperationStatusModel deleteUser(@PathVariable String id) {
	
		qS.deleteQuiz(id);

		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationNames.DELETE.name());
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		return returnValue;
	}
	
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}) //MediaType.APPLICATION_XML_VALUE
	public List<QuizRest> getQuizzes(@AuthenticationPrincipal User user){
		List<QuizRest> returnValue = new ArrayList<>();
		
		String loggedUserName=user.getUsername();
		
		List<QuizDto> quizzes = qS.getQuizzes(loggedUserName);
		
		for (QuizDto quizDto:quizzes ) {		
			
			returnValue.add(new ModelMapper().map(quizDto,QuizRest.class));
		}
		return returnValue;	
	}
	
	

}

	
	

