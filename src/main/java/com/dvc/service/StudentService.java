package com.dvc.service;

import com.dvc.model.Student;
import com.dvc.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        log.info("Creating student: {}", student); // Log with INFO level
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        log.warn("Deleting student with ID: {}", studentId); // Log with WARN level
        studentRepository.deleteById(studentId);
    }

    public Student getStudentById(Long studentId) {
        Student student = studentRepository.findAll()
                .stream()
                .filter(s -> s.getStudentID().equals(studentId))
                .findFirst().orElse(null);
        if (student != null) {
            log.debug("Found student by ID: {}", studentId); // Log with DEBUG level
        } else {
            log.warn("Student not found with ID: {}", studentId); // Log with WARN level
        }
        return student;
    }

    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        log.info("Retrieved all students: {}", students.size()); // Log with INFO level
        return students;
    }

    public Student updateStudent(Long studentId, Student student) {
        Student studentToUpdate = getStudentById(studentId);
        if (studentToUpdate != null) {
            studentToUpdate.setFirstName(student.getFirstName());
            studentToUpdate.setLastName(student.getLastName());
            studentToUpdate.setStudentEmail(student.getStudentEmail());
            log.info("Updating student with ID: {}", studentId); // Log with INFO level
            return studentRepository.save(studentToUpdate);
        } else {
            log.warn("Student not found for update with ID: {}", studentId); // Log with WARN level
            return null;
        }
    }
}