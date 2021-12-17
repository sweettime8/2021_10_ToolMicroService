package com.mrd.learn.service.impl;

import com.mrd.learn.entity.Quiz;
import com.mrd.learn.entity.dto.QuizAnswerDTO;
import com.mrd.learn.entity.dto.QuizSearchForm;
import com.mrd.learn.repository.QuizRepositoryCustom;
import com.mrd.learn.service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ducnh
 */

@Service
public class QuizServiceImpl implements QuizService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuizService.class);

    @Autowired
    private QuizRepositoryCustom quizRepositoryCustom;

    @Override
    public List<Quiz> getAll(QuizSearchForm searchForm, int currentPage, int rowPerPage) {
        return quizRepositoryCustom.getAll(searchForm, currentPage, rowPerPage);
    }

    @Override
    public List<QuizAnswerDTO> search(QuizSearchForm searchForm, int currentPage, int rowPerPage) {
        return quizRepositoryCustom.search(searchForm, currentPage, rowPerPage);
    }

    @Override
    public long count(QuizSearchForm searchForm) {
        return 0;
    }
}
