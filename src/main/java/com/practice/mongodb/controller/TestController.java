package com.practice.mongodb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/test")
public class TestController {

    @GetMapping("/message")
    public ResponseEntity<String> sayHello() {
        return new ResponseEntity<>("hellow world", HttpStatus.OK);
    }

}
