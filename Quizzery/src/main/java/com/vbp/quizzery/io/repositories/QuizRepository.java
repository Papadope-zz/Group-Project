package com.vbp.quizzery.io.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vbp.quizzery.io.entity.QuizEntity;

@Repository
public interface QuizRepository extends PagingAndSortingRepository<QuizEntity, Long> {

	public QuizEntity findByQuizId(String quizId);

	@Query(value = " SELECT * FROM quizzes INNER JOIN users u On  users_Id=u.id where u.email= :email", nativeQuery = true)
	public List<QuizEntity> getQuizzesByUserEmail(@Param("email") String email);

}
