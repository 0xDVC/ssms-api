package com.dvc.controller;


import com.dvc.model.Student;
import com.dvc.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    private static final String STUDENT_URL_ID = "/{studentSlug}";
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student));
    }

    @DeleteMapping(STUDENT_URL_ID)
    public ResponseEntity<Student> deleteStudent(@PathVariable int studentSlug) {
        studentService.deleteStudent(studentSlug);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(STUDENT_URL_ID)
    public ResponseEntity<Student> getStudent(@PathVariable int studentSlug) {
        return ResponseEntity.ok(studentService.getStudentBySlug(studentSlug));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PutMapping(STUDENT_URL_ID)
    public ResponseEntity<Student> updateStudent(@PathVariable int studentSlug, @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(studentSlug, student));
    }
}

