package com.dw.lms.service;

import com.dw.lms.model.Learning_contents_qa;
import com.dw.lms.repository.LearningContentsQARepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LearningContentsQAService {
    @Autowired
    LearningContentsQARepository learningContentsQARepository;

    public List<Learning_contents_qa> findByQAKey(String lectureId, Long learningContentsSeq) {
        return learningContentsQARepository.findByQAKey(lectureId, learningContentsSeq);
    }

}
