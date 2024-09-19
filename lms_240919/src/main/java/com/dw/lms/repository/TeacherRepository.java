package com.dw.lms.repository;

import com.dw.lms.model.CK.Teacher_CK;
import com.dw.lms.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Teacher_CK> {
    List<Teacher> findByLecture_LectureId(String lectureId);
}
