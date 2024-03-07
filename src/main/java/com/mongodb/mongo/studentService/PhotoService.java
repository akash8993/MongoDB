package com.mongodb.mongo.studentService;

import com.mongodb.mongo.model.Photo;
import com.mongodb.mongo.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public String addPhoto(String originalFilename, MultipartFile image) throws IOException {
        Photo photo= new Photo();
        photo.setTitle(originalFilename);
        photo.setPhoto(new Binary(BsonBinarySubType.BINARY,image.getBytes()));
       return photoRepository.save(photo).getId();
    }

    public Photo getPhoto(String id) {
        return photoRepository.findById(id).get();
    }
}
