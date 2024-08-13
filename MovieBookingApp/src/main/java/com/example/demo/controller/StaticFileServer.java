package com.example.demo.controller;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticFileServer {

    @GetMapping("/api/files/{filename}")
    public ResponseEntity<?> getImage(@PathVariable String filename) throws IOException {
    	try {
        ClassPathResource imgFile = new ClassPathResource(filename);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    	} catch ( Exception e ) {
    		System.out.println(e.getMessage());
    		return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
    	}
    }
}