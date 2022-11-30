package com.reactiveCRUD.reactiveSample.service;

import com.reactiveCRUD.reactiveSample.model.Student;
import com.reactiveCRUD.reactiveSample.repository.RegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Mono getById(String id) {
        return registrationRepository.findById(id);
    }

    public Mono save(Student student) {
        return registrationRepository.save(student);
    }
}
