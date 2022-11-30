package com.reactiveCRUD.reactiveSample.model;

import com.mongodb.lang.Nullable;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@Document
@ToString
@Getter
@Setter
public class Student {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Nullable
    private int age;
    @Nullable
    private double gpa;

}
