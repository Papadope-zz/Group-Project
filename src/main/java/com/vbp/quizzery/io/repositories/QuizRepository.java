package com.vbp.quizzery.io.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.vbp.quizzery.io.entity.QuizEntity;
import com.vbp.quizzery.io.entity.UserEntity;


@Repository
public interface QuizRepository extends CrudRepository<QuizEntity, Long> {
	
	public QuizEntity findByQuizId(String quizId);
	public List<QuizEntity> findAllByUser(UserEntity loggedUser);
}
