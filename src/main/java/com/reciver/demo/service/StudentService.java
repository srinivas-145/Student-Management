package com.reciver.demo.service;

import com.reciver.demo.entity.Student;
import com.reciver.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> updateMarks(Long id, Integer marks1, Integer marks2, Integer marks3) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setMarks1(marks1);
            student.setMarks2(marks2);
            student.setMarks3(marks3);
            studentRepository.save(student);
        }
        return studentOpt;
    }
}
