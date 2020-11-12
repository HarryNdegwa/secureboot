package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    @GetMapping("/")
    public ResponseEntity<?> students() {

        List<String> students = new ArrayList<>();

        students.add("Harry");
        students.add("Kevin");
        students.add("Mike");

        return ResponseEntity.ok().body(students);
    }

}
