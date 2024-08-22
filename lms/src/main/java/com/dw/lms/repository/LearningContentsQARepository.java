package com.dw.lms.repository;

import com.dw.lms.model.CK.Learning_contents_CK;
import com.dw.lms.model.Learning_contents;
import com.dw.lms.model.Learning_contents_qa;
import com.dw.lms.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LearningContentsQARepository extends JpaRepository<Learning_contents_qa, Long> {


//    @Query("SELECT u FROM User u WHERE u.userNameKor LIKE :userName")
//    List<User> findByUserNameLike(@Param("userName") String userName);

// @Query("SELECT u FROM Learning_contents_qa u WHERE u.lecture = :lectureId AND u.learningContentsSeq = :learningContentsSeq")

    @Query("SELECT u FROM Learning_contents_qa u WHERE u.learning_contents.lecture.id = :lectureId AND u.learning_contents.learningContentsSeq = :learningContentsSeq")
    List<Learning_contents_qa> findByQAKey(@Param("lectureId") String lectureId, @Param("learningContentsSeq") Long learningContentsSeq);

}
