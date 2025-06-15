package com.example.service;

import com.example.model.MiEvento;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventConsumerService {
    @KafkaListener(topics = "${topic.name}")
    public void listenFromTopic(MiEvento event) {
        log.info("Mensaje recibido: " + event);
    }
}
