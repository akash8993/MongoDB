package com.mongodb.mongo.controller;

import com.mongodb.mongo.model.Photo;
import com.mongodb.mongo.studentService.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/photo")
@RequiredArgsConstructor
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @PostMapping("/add")
    public String addPhoto(@RequestParam("image")MultipartFile image) throws IOException {
        String id= photoService.addPhoto(image.getOriginalFilename(),image);
        return id;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable String id)
    {
        Photo photo= photoService.getPhoto(id);
        Resource resource= new ByteArrayResource(photo.getPhoto().getData());
       return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachement; filename=\""+photo.getTitle()+"\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
       // return ResponseEntity.status(HttpStatus.OK).body(resource);
    }
}
