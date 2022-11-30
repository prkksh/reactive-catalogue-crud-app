package com.reactiveCRUD.reactiveSample.service;

import com.reactiveCRUD.reactiveSample.model.Student;
import com.reactiveCRUD.reactiveSample.repository.RegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@AllArgsConstructor
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public Flux<Student> getAllStudents() {
        return registrationRepository.findAll().switchIfEmpty(Flux.empty());
    }

    public Mono<ResponseEntity<Student>> getById(String id) {
        return registrationRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono updateById(String id, Student student) {
        return registrationRepository.findById(id).flatMap(studentFromDoc -> {
            studentFromDoc.setFirstName(student.getFirstName());
            studentFromDoc.setLastName(student.getLastName());
            return registrationRepository.save(studentFromDoc);
        }).map(ResponseEntity::ok)
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public Mono<ResponseEntity<Student>> save(Student student) {
        return registrationRepository.save(student)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
