package com.mongodb.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection= "Student")
public class Student {

    @Id
    private String id;

    private String name;

    private String address;

    private int age;

    @DocumentReference(collection = "student-category")
    private StudentCategory studentCategory;

    @DocumentReference(collection = "student-marks")
    private StudentMarks studentMarks;
}
