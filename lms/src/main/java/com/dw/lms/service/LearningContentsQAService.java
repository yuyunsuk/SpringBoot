package com.dw.lms.service;

import com.dw.lms.dto.LearningContentsQADto;
import com.dw.lms.model.LearningContentsQA;
import com.dw.lms.repository.LearningContentsQARepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LearningContentsQAService {
    @Autowired
    LearningContentsQARepository learningContentsQARepository;

    public List<LearningContentsQADto> findByQAKey(String lectureId, Long learningContentsSeq) {

        List<LearningContentsQADto> learningContentsQADtoList = new ArrayList<>();
        List<LearningContentsQA> learningContentsQAList = learningContentsQARepository.findByQAKey(lectureId, learningContentsSeq);

        // Assuming Learning_contents_qa class has getter methods for each field
        for (LearningContentsQA qa : learningContentsQAList) {
            LearningContentsQADto dto = new LearningContentsQADto();

            // Assuming appropriate setter methods or constructor
            dto.setLectureId(qa.getLearning_contents().getLecture().getLectureId());
            dto.setLectureContentSeq(qa.getLearning_contents().getLearningContentsSeq());
            dto.setQuestionSeq(qa.getQuestionSeq());
            dto.setQuestion(qa.getQuestionContent());
            dto.setAnswer1(qa.getAnswer1Content());
            dto.setAnswer2(qa.getAnswer2Content());
            dto.setAnswer3(qa.getAnswer3Content());
            dto.setAnswer4(qa.getAnswer4Content());
            dto.setCorrectAnswer(qa.getCorrectAnswer());

            learningContentsQADtoList.add(dto);
        }

        return learningContentsQADtoList;
    }

}
