package com.dw.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LearningContentsQADto {
    private String lectureId;
    private Long lectureContentSeq;
    private Integer questionSeq;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private Integer correctAnswer;
}
