package com.reactiveCRUD.reactiveSample.controller;

import com.reactiveCRUD.reactiveSample.model.Student;
import com.reactiveCRUD.reactiveSample.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public Mono<ResponseEntity<Student>> getStudentById(@PathVariable("id") final String id) {
        return registrationService.getById(id);
    }

    @PutMapping("{id}")
    public Mono updateStudentById(@PathVariable("id") final String id,
                                  @RequestBody final Student student) {
        return registrationService.updateById(id, student);
    }

    @PostMapping
    public Mono<ResponseEntity<Student>> create(@RequestBody final Student student) {
        return registrationService.save(student);
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteStudent(@PathVariable("id") final String id) {
        return registrationService.deleteById(id);
    }
}
