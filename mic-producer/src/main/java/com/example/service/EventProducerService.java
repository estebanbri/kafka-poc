package com.example.service;

import com.example.model.MiEvento;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventProducerService {

    private static final String SUCCESS_LOG_MESSAGE = "Mensaje enviado event: {}, partition: {}, offset: {}";
    private static final String ERROR_LOG_MESSAGE = "Mensaje no enviado event: {}, motivo: {}";
    @Value("${topic.name}")
    private String topicName;

    private final KafkaTemplate<String, MiEvento> kafkaTemplate;
    public void sendTopic(String message) {
        final var event = new MiEvento(message);
        CompletableFuture<SendResult<String, MiEvento>> future = kafkaTemplate.send(topicName, event);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info(SUCCESS_LOG_MESSAGE, event, result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
            } else {
                log.error(ERROR_LOG_MESSAGE, event, ex.getMessage());
            }
        });
    }
}
