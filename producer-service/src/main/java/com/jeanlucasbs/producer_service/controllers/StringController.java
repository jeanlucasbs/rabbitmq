package com.jeanlucasbs.producer_service.controllers;

import com.jeanlucasbs.producer_service.services.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produces")
public class StringController {

    @Autowired
    private StringService service;

    @GetMapping
    public ResponseEntity<String> produces(@RequestParam("message") String message){
        service.produce(message);
        return ResponseEntity.ok().body("Sending message");
    }
}
