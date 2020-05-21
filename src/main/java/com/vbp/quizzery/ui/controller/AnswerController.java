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
import org.springframework.web.bind.annotation.RestController;

import com.vbp.quizzery.service.AnswerService;
import com.vbp.quizzery.service.QuestionService;
import com.vbp.quizzery.service.QuizService;
import com.vbp.quizzery.service.UserService;
import com.vbp.quizzery.shared.dto.AnswerDto;
import com.vbp.quizzery.ui.model.request.AnswerRequestModel;
import com.vbp.quizzery.ui.model.response.AnswerRest;
import com.vbp.quizzery.ui.model.response.OperationStatusModel;
import com.vbp.quizzery.ui.model.response.RequestOperationNames;
import com.vbp.quizzery.ui.model.response.RequestOperationStatus;



@RestController
@RequestMapping("/quizzes/{Id}/questions/{qId}/answers")
public class AnswerController {

	@Autowired
	UserService uS;
	@Autowired
	QuizService qS;
	@Autowired
	QuestionService queS;
	@Autowired
	AnswerService aS;
	
	
	@GetMapping(path="/",
			produces = {MediaType.APPLICATION_JSON_VALUE}) //MediaType.APPLICATION_XML_VALUE, 
	public List<AnswerRest> getQuestionAnswers(@PathVariable String qId) {
			
//		AddressDto addressesDto=aS.getAddress(aId);
		List<AnswerRest> returnValue = new ArrayList<>();
		List<AnswerDto> answers=aS.getAnswers(qId);
		
		if (answers!=null && !answers.isEmpty()) {
		java.lang.reflect.Type listType = new TypeToken<List<AnswerRest>>() {}.getType();
		returnValue = new ModelMapper().map(answers, listType);
		}
		
		return  returnValue;
}
	
	
	@GetMapping(path="/{aId}",
			produces = {MediaType.APPLICATION_JSON_VALUE}) //MediaType.APPLICATION_XML_VALUE, 
		public AnswerRest getAnswer(@PathVariable String aId) {
		
//		AddressDto addressesDto=aS.getAddress(aId);
	
		return new ModelMapper().map(aS.getAnswer(aId), AnswerRest.class);
}
	
	
	@PostMapping(path="/",
			consumes={MediaType.APPLICATION_JSON_VALUE}, //MediaType.APPLICATION_XML_VALUE, 
			produces = {MediaType.APPLICATION_JSON_VALUE})//MediaType.APPLICATION_XML_VALUE,
	
	public AnswerRest createAnswer(@RequestBody AnswerRequestModel answerDetails, @PathVariable String qId) throws Exception {
		
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//System.out.println(auth.getPrincipal());
	
		if ((answerDetails.getAnswerText().isEmpty())||(answerDetails.getCorrect()==null)) throw new NullPointerException ("The Object is null");
		
		ModelMapper mM = new ModelMapper();

		AnswerDto answerDto = mM.map(answerDetails, AnswerDto.class);

		AnswerDto createdAnswer = aS.createAnswer(qId, answerDto);

		AnswerRest returnValue = mM.map(createdAnswer,AnswerRest.class);
		return returnValue;
	}
	
	@PutMapping(path = "/{aId}")
	public AnswerRest updateAnswer(@PathVariable String aId, @RequestBody AnswerRequestModel answerDetails) {

		AnswerRest returnValue = new AnswerRest();

		AnswerDto answerDto = new ModelMapper().map(answerDetails, AnswerDto.class);

		AnswerDto updatedAnswer = aS.updateAnswer(aId, answerDto);

		BeanUtils.copyProperties(updatedAnswer, returnValue);

		return returnValue;

	}
	

	@DeleteMapping(path = "/{aId}")
	public OperationStatusModel deleteAnswer(@PathVariable String aId) {
		OperationStatusModel returnValue = new OperationStatusModel();

		returnValue.setOperationName(RequestOperationNames.DELETE.name());

		aS.deleteAnswer(aId);

		returnValue.setOperationName(RequestOperationStatus.SUCCESS.name());
		return returnValue;

	}
	
}
