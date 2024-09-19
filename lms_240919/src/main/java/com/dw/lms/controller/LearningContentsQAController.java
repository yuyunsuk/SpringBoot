package com.dw.lms.controller;

import com.dw.lms.dto.LearningContentsQADto;
import com.dw.lms.model.LearningContentsQA;
import com.dw.lms.service.LearningContentsQAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LearningContentsQAController {
    @Autowired
    LearningContentsQAService learningContentsQAService;

    @GetMapping("/learning/contents/qa/{lectureId}/{learningContentsSeq}")
    public ResponseEntity<List<LearningContentsQADto>> findByQAKey(@PathVariable String lectureId, @PathVariable Long learningContentsSeq) {
        return new ResponseEntity<>(learningContentsQAService.findByQAKey(lectureId, learningContentsSeq), HttpStatus.OK);
    }

}
