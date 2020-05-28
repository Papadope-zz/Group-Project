package com.vbp.quizzery.io.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vbp.quizzery.io.entity.AnswerEntity;
import com.vbp.quizzery.io.entity.QuestionEntity;

@Repository
public interface AnswerRepository extends CrudRepository<AnswerEntity, Long> {

	public List<AnswerEntity> findAllByQuestion(QuestionEntity question);

	public AnswerEntity findByAnswerId(String answerId);

	public Long deleteByQuestion(QuestionEntity question);

}
