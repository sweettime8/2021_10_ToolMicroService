package com.mrd.learn.controller;

import com.mrd.learn.constant.Constant;
import com.mrd.learn.entity.Quiz;
import com.mrd.learn.entity.dto.QuizAnswerDTO;
import com.mrd.learn.entity.dto.QuizSearchForm;
import com.mrd.learn.message.MessageContent;
import com.mrd.learn.message.ResponseMessage;
import com.mrd.learn.service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ducnh
 */
@RestController
@RequestMapping("/learn/api/quiz")
public class QuizController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    private QuizService quizService;

    @GetMapping
    public ResponseMessage getAllQuizz(@RequestParam(value = "currentPage") int currentPage,
                                             @RequestParam(value = "rowsPerPage") int rowsPerPage,
                                             @RequestParam(value = "sort", required = false) String sort,
                                             @RequestHeader Map<String, String> headerParam) {
        ResponseMessage response = null;
        if (currentPage == 0 || rowsPerPage == 0) {
            response = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), Constant.VALIDATION_INVALID_PARAM_VALUE,
                    new MessageContent(null));
        }else{
            QuizSearchForm quizSearchForm = new QuizSearchForm();
            quizSearchForm.setSort(sort);
            List<Quiz> quizList = quizService.getAll(quizSearchForm , currentPage, rowsPerPage);
            long total = quizService.count(quizSearchForm);
            if(quizList != null){
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(quizList, total));
            }else{
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(null, total));
            }
        }
        return response;
    }

    @GetMapping("/quiz-answer")
    public ResponseMessage getAllQuizzAnswer(@RequestParam(value = "currentPage") int currentPage,
                                      @RequestParam(value = "rowsPerPage") int rowsPerPage,
                                      @RequestParam(value = "sort", required = false) String sort,
                                      @RequestHeader Map<String, String> headerParam) {
        ResponseMessage response = null;
        if (currentPage == 0 || rowsPerPage == 0) {
            response = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), Constant.VALIDATION_INVALID_PARAM_VALUE,
                    new MessageContent(null));
        }else{
            QuizSearchForm quizSearchForm = new QuizSearchForm();
            quizSearchForm.setSort(sort);
            List<QuizAnswerDTO> quizAnswerDTOList = quizService.search(quizSearchForm , currentPage, rowsPerPage);
            long total = quizService.count(quizSearchForm);
            if(quizAnswerDTOList != null){
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(quizAnswerDTOList, total));
            }else{
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(null, total));
            }
        }
        return response;
    }
}
