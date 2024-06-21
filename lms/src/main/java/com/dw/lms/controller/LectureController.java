package com.dw.lms.controller;

import com.dw.lms.dto.LectureCategoryCountDto;
import com.dw.lms.model.Lecture;
import com.dw.lms.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LectureController {
    @Autowired
    LectureService lectureService;

    @GetMapping("/lecture")
    public ResponseEntity<List<Lecture>> getAllLecture() {
        return new ResponseEntity<>(lectureService.getAllLecture(),
                HttpStatus.OK);
    }

    @GetMapping("/lecture/{lectureId}")
    public ResponseEntity<Lecture> getLecture(@PathVariable String lectureId){
        return new ResponseEntity<>(lectureService.getLecture(lectureId), HttpStatus.OK);
    }
    
    @GetMapping("/lecture/categoryCount")
    public ResponseEntity<List<LectureCategoryCountDto>> getLectureCategoryCountJPQL() {
        return new ResponseEntity<>(lectureService.getLectureCategoryCountJPQL(),
                HttpStatus.OK);
    }
    
    @PostMapping("/lecture/lectureList")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')") // ADMIN, USER 이외에는 사용 못하게
    public List<Lecture> saveLecture(@RequestBody List<Lecture> lectureList) {
        return lectureService.saveLectureList(lectureList);
    }
    
}
