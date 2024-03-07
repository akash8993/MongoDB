package com.mongodb.mongo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "photo")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Photo {
    @Id
    private String id;
    private String title;
    private Binary photo;
}
