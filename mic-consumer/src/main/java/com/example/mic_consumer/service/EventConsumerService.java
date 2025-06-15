package com.example.mic_consumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventConsumerService {
    @KafkaListener(topics = "${topic.name}")
    public void listenFromTopic(String event) {
        log.info("Mensaje recibido: " + event);
    }
}
