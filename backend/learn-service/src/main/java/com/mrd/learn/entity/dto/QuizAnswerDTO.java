package com.mrd.learn.entity.dto;

import com.mrd.learn.entity.Quiz;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author ducnh
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizAnswerDTO implements Serializable {
    private Quiz quiz;
    private List<AnswerDTO> lstAnswer;
}
