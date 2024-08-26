package com.dw.lms.repository;

import com.dw.lms.model.LearningContentsQA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LearningContentsQARepository extends JpaRepository<LearningContentsQA, Long> {
    @Query("SELECT u FROM LearningContentsQA u WHERE u.learning_contents.lecture.id = :lectureId AND u.learning_contents.learningContentsSeq = :learningContentsSeq")
    List<LearningContentsQA> findByQAKey(@Param("lectureId") String lectureId, @Param("learningContentsSeq") Long learningContentsSeq);
}
