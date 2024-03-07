package com.mongodb.mongo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

/** Here Json Include is used for a verification step where we know that if the field is not null then
 * only persist to database **/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection= "Student")
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @DocumentReference(collection = "student-address")
    private List<Address> addressList;
}
