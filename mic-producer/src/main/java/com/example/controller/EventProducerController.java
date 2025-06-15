package com.example.controller;

import com.example.service.EventProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventProducerController {
    private final EventProducerService eventProducerService;
    @GetMapping("/send/{message}")
    public ResponseEntity<String> sendToTopic(@PathVariable String message) {
        this.eventProducerService.sendTopic(message);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
