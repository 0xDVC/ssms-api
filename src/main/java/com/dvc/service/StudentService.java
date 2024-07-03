package com.dvc.service;

import com.dvc.model.Student;
import com.dvc.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
         studentRepository.deleteById(studentId);
    }
    public Student getStudentById(Long studentId) {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getStudentID().equals(studentId))
                .findFirst().orElse(null);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Long studentId, Student student) {
        Student studentToUpdate = getStudentById(studentId);
        if (studentToUpdate != null) {
            studentToUpdate.setFirstName(student.getFirstName());
            studentToUpdate.setLastName(student.getLastName());
            studentToUpdate.setStudentEmail(student.getStudentEmail());
            return studentRepository.save(studentToUpdate);
        }
        return null;
    }
}
