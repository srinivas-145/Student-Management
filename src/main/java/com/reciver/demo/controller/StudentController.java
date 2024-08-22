package com.reciver.demo.controller;

import com.reciver.demo.entity.Student;
import com.reciver.demo.models.Users;
import com.reciver.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<?> createStudent(@Valid @RequestBody Student student) {
        if (student.getAge() < 15 || student.getAge() > 20) {
            return ResponseEntity.badRequest().body("Age must be between 15 and 20 years");
        }
        student = studentService.createStudent(student);
        return ResponseEntity.ok(student);
    }
    
    @RequestMapping("/hello")
	public String getAllUsers(){
		
		return "hi";
		
	}

    @PutMapping("/{id}/marks")
    public ResponseEntity<?> updateMarks(
            @PathVariable Long id,
            @RequestParam @Min(0) @Max(100) Integer marks1,
            @RequestParam @Min(0) @Max(100) Integer marks2,
            @RequestParam @Min(0) @Max(100) Integer marks3) {

        Optional<Student> studentOpt = studentService.updateMarks(id, marks1, marks2, marks3);
        if (studentOpt.isPresent()) {
            return ResponseEntity.ok(studentOpt.get());
        } else {
            return ResponseEntity.badRequest().body("Student not found");
        }
    }
    
    @ExceptionHandler
    public ResponseEntity<String> handleValidationExceptions(ConstraintViolationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
