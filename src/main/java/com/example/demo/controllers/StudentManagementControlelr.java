package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementControlelr {

    private List<String> initialData() {
        List<String> students = new ArrayList<>();

        students.add("Harry");
        students.add("Mike");
        students.add("Jane");
        students.add("Anne");
        students.add("Smith");

        return students;
    }

    @GetMapping("/")
    public ResponseEntity<?> studentManagement() {
        return ResponseEntity.ok().body(initialData());
    }

    @PostMapping("/add/student")
    public ResponseEntity<?> registerStudent(@RequestBody String student) {
        List<String> students = initialData();
        String newStudent = student;
        students.add(newStudent);
        return ResponseEntity.ok().body("Created user!");
    }

    @DeleteMapping("/delete/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id) {
        List<String> students = initialData();
        try {
            students.remove(id);
        } catch (Exception e) {
            System.out.println("Error deleting user");
        }
        return ResponseEntity.ok().body("Deleted student!");

    }

}
