package com.mongodb.mongo.controller;

import com.mongodb.mongo.model.Student;
import com.mongodb.mongo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    private final StudentRepository studentRepository;

    @PostMapping("/save")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student)
    {
        return ResponseEntity.status(HttpStatus.OK).body(studentRepository.save(student));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") String id)
    {
       Optional<Student> student= studentRepository.findById(id);
       if(student.isPresent())
       {
           return ResponseEntity.status(HttpStatus.OK).body(student.get());
       }
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
