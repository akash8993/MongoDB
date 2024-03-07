package com.mongodb.mongo.studentService;

import com.mongodb.mongo.model.Student;
import com.mongodb.mongo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    private  final MongoTemplate mongoTemplate;

    public List<Document> getOldestPersonByCity() {
        UnwindOperation unwindOperation= Aggregation.unwind("addressList");

        SortOperation sortOperation= Aggregation.sort(Sort.Direction.DESC,"age");

        GroupOperation groupOperation= Aggregation.group("addressList.city").first(Aggregation.ROOT)
                .as("oldestPerson");

        Aggregation aggregation= Aggregation.newAggregation(unwindOperation, sortOperation, groupOperation);
        List<Document> student= mongoTemplate.aggregate(aggregation, Student.class,Document.class).getMappedResults();
        return student;
    }
}
