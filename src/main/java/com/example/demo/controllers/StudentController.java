package com.example.demo.controllers;

import java.util.Arrays;
import java.util.List;

import com.example.demo.models.Student;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    // List<String> students = new ArrayList<>();

    List<Student> students = Arrays.asList(new Student("Mike", "mike24"), new Student("Tom", "Tom24"),
            new Student("Linda", "Linda24"));

    @GetMapping("/")
    public ResponseEntity<?> students() {
        return ResponseEntity.ok().body(students);
    }

}
