package com.mrd.learn.service;

import com.mrd.learn.entity.Quiz;
import com.mrd.learn.entity.dto.QuizAnswerDTO;
import com.mrd.learn.entity.dto.QuizSearchForm;

import java.util.List;

/**
 * @author ducnh
 */
public interface QuizService {
    List<Quiz> getAll(QuizSearchForm searchForm, int currentPage, int rowPerPage);

    List<QuizAnswerDTO> search(QuizSearchForm searchForm, int currentPage, int rowPerPage);

    long count(QuizSearchForm searchForm);
}
