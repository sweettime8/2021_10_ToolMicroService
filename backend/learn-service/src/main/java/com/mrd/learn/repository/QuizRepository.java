package com.mrd.learn.repository;

import com.mrd.learn.entity.Quiz;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ducnh
 */
@Repository
public interface QuizRepository extends PagingAndSortingRepository<Quiz, Integer>, JpaSpecificationExecutor<Quiz> {
}
