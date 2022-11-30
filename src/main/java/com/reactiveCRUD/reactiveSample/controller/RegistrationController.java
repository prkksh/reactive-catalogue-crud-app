package com.reactiveCRUD.reactiveSample.controller;

import com.reactiveCRUD.reactiveSample.model.Student;
import com.reactiveCRUD.reactiveSample.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("v1/api")
@AllArgsConstructor
@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public Flux<Student> getAllStudents() {
        return registrationService.getAllStudents();
    }

    @GetMapping("{id}")
    public Mono getStudentById(@PathVariable("id") final String id) {
        return registrationService.getById(id);
    }

    @PostMapping
    public Mono save(@RequestBody final Student student) {
        return registrationService.save(student);
    }
}
