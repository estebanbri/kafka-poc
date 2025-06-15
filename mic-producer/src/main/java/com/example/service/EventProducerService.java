package com.example.service;

import com.example.model.MiEvento;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventProducerService {
    @Value("${topic.name}")
    private String topicName;

    private final KafkaTemplate<String, MiEvento> kafkaTemplate;
    public void sendTopic(String message) {

       kafkaTemplate.send(topicName, new MiEvento(message));
       log.info("Mensaje enviado: " + message);
    }
}
