package com.reactiveCRUD.reactiveSample.repository;

import com.reactiveCRUD.reactiveSample.model.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends ReactiveMongoRepository<Student, String> {
}
