package com.kafka.lab.kafkalab;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "${topic.name}")
    public void listen(ConsumerRecord<String, String> payload, Acknowledgment ack) throws InterruptedException {
        // faz algum processamento aqui
        Thread.sleep(50);
        logger.info(String.format("==> Consumed message %s, offset %s, partition %s", payload.value(), payload.offset(), payload.partition()));
        ack.acknowledge();
    }
}
