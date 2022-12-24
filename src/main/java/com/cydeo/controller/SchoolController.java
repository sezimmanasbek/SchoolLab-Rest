package com.cydeo.controller;

import com.cydeo.service.StudentService;
import com.cydeo.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school")
public class SchoolController {

    private final StudentService studentService;
    private final TeacherService teacherService;

    public SchoolController(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public ResponseEntity getTeachers(){
        return ResponseEntity.ok(teacherService.findAll());

    }
}
