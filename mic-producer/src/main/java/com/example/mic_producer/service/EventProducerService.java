package com.example.mic_producer.service;

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

    private static final String SUCCESS_LOG_MESSAGE = "Mensaje enviado: {}, partition: {}, offset: {}";
    private static final String ERROR_LOG_MESSAGE = "Mensaje no enviado: {}, motivo: {}";
    @Value("${topic.name}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;
    public void sendTopic(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info(SUCCESS_LOG_MESSAGE, message, result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
            } else {
                log.error(ERROR_LOG_MESSAGE, message, ex.getMessage());
            }
        });
    }
}
