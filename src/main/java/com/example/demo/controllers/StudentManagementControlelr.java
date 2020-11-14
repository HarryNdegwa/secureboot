package com.example.demo.controllers;

import java.util.Arrays;
import java.util.List;

import com.example.demo.models.Student;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementControlelr {

    List<Student> students = Arrays.asList(new Student("Mike", "mike24"), new Student("Tom", "Tom24"),
            new Student("Linda", "Linda24"));

    // hasRole('ROLE_'),hasAnyRole('ROLE_')
    // hasAuthority('permission'),hasAnyAuthority('permission')
    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
    public ResponseEntity<?> studentManagement() {
        return ResponseEntity.ok().body(students);
    }

    @PostMapping("/add/student")
    @PreAuthorize("hasAuthority('course:write')")
    public ResponseEntity<?> registerStudent(@RequestBody Student student) {
        return ResponseEntity.ok().body("Created user!");
    }

    @DeleteMapping("/delete/student")
    @PreAuthorize("hasAuthority('course:write')")
    public ResponseEntity<?> deleteStudent() {
        return ResponseEntity.ok().body("Deleted student!");
    }

}
