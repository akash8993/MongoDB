package com.mongodb.mongo.controller;

import com.mongodb.mongo.model.Student;
import com.mongodb.mongo.repository.StudentRepository;
import com.mongodb.mongo.studentService.StudentService;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    private final StudentService service;
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

    @GetMapping("/get/{name}")
    public List<Student> getStudentNameStartWith(@RequestParam("name") String name)
    {
        return studentRepository.findByNameStartsWith(name);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable("id") String id)
    {
        studentRepository.deleteById(id);
    }

    @GetMapping("/age")
    public List<Student> getStudentByAge(@RequestParam Integer min, @RequestParam Integer max)
    {
        List<Student> data= studentRepository.findPersonAgeBetween(min,max);

        return data;
    }

    @GetMapping("/ageWithoutFields")
    public List<Student> getStudentByAgeWithoutFields(@RequestParam Integer min, @RequestParam Integer max)
    {
        List<Student> data= studentRepository.findPersonAgeBetweenExcludingFields(min,max);

        return data;
    }

    @GetMapping("/getOldestPerson")
    public List<Document>  getOldestPerson()
    {
        return service.getOldestPersonByCity();
    }

}
