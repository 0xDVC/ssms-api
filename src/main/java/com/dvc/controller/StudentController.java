package com.dvc.controller;


import com.dvc.model.Student;
import com.dvc.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
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
    public ResponseEntity<Student> deleteStudent(@RequestBody int studentSlug) {
        studentService.deleteStudent(studentSlug);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(STUDENT_URL_ID)
    public ResponseEntity<Student> getStudent(@RequestBody int studentSlug) {
        return ResponseEntity.ok(studentService.getStudentBySlug(studentSlug));
    }

    @GetMapping
    public ResponseEntity<Student> getStudents() {
        return ResponseEntity.ok((Student) studentService.getAllStudents());
    }

    @PutMapping(STUDENT_URL_ID)
    public ResponseEntity<Student> updateStudent(@RequestBody int studentSlug, @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(studentSlug, student));
    }
}
