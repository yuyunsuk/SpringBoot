package com.dw.lms.controller;

import com.dw.lms.model.Code_class_detail;
import com.dw.lms.service.Code_class_detailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lms")
public class Code_class_detailController {
    @Autowired
    Code_class_detailService code_class_detailService;

    @GetMapping("/codeClassDetail")
    public ResponseEntity<List<Code_class_detail>> getAllCodeClass() {

        System.out.println("getAllCodeClass: 조회");

        return new ResponseEntity<>(code_class_detailService.getAllCodeClassDetail(),
                HttpStatus.OK);
    }

    @GetMapping("/codeClassDetail/{codeClass}/{code}")
    public ResponseEntity<Code_class_detail> getCodeClassByKey(@PathVariable String codeClass, @PathVariable String code) {
        return new ResponseEntity<>(code_class_detailService.getCodeClassByKey(codeClass, code),
                HttpStatus.OK);
    }

    @PostMapping("/saveCodeClassDetail")
    public ResponseEntity<String> saveCodeClassDetail(@RequestBody Code_class_detail code_class_detail) {
        return new ResponseEntity<>(code_class_detailService.saveCodeClassDetail(code_class_detail),
                HttpStatus.OK);
    }



}
