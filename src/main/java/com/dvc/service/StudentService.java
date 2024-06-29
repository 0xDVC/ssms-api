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

    public void deleteStudent(int studentSlug) {
         studentRepository.delete(getStudentBySlug(studentSlug));
    }
    public Student getStudentBySlug(int studentSlug) {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getStudentSlug() == studentSlug)
                .findFirst().orElse(null);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(int studentSlug, Student student) {
        Student studentToUpdate = getStudentBySlug(studentSlug);

        studentToUpdate.setFirstName(student.getFirstName());
        studentToUpdate.setLastName(student.getLastName());
        studentToUpdate.setStudentEmail(student.getStudentEmail());
        return studentRepository.save(studentToUpdate);
    }
}
