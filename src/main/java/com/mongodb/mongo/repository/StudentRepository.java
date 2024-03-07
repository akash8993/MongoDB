package com.mongodb.mongo.repository;

import com.mongodb.mongo.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {

    List<Student> findByNameStartsWith(String name);

    //Ye neeche wala jpql ki teah hai and data le aaega ye
    List<Student> findByAgeBetween(Integer min, Integer max);

    @Query(value = "{'age':{$gt:?0, $lt :?1}}")
    List<Student> findPersonAgeBetween(Integer min, Integer max);

    @Query(value = "{'age':{$gt:?0, $lt :?1}}", fields = "{addressList:0}")
    List<Student> findPersonAgeBetweenExcludingFields(Integer min, Integer max);
}
