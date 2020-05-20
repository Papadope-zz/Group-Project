package com.vbp.quizzery.io.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.vbp.quizzery.io.entity.QuizEntity;


@Repository
public interface QuizRepository extends PagingAndSortingRepository<QuizEntity, Long> {
	
	public QuizEntity findByQuizId(String quizId);
}
