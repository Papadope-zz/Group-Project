package com.vbp.quizzery.io.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.vbp.quizzery.io.entity.QuestionEntity;
import com.vbp.quizzery.io.entity.QuizEntity;


@Repository
public interface QuestionRepository extends PagingAndSortingRepository<QuestionEntity, Long> {

	public List<QuestionEntity> findAllByQuiz(QuizEntity quiz);
	public QuestionEntity findByQuestionId(String questionId);
	
}
