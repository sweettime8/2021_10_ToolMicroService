package com.mrd.learn.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ducnh
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO implements Serializable {
    int id;
    int quizId;
    String content;
    boolean correct;
    String description;

}
