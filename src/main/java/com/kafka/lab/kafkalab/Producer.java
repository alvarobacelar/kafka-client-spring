package com.kafka.lab.kafkalab;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message){
        for (int i=0; i < 100000; i++){
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, message);
            producerRecord.headers().add(new RecordHeader("valor", "aqui o valor".getBytes()));

            kafkaTemplate.send(producerRecord).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onFailure(Throwable ex) {
                    logger.error(String.format("!!! Unable to send message = %s, to topic = %s, because of error = %s", producerRecord.value(), producerRecord.topic(), ex));
                }

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    logger.info(String.format("==> Message was successfully sent = %s, partition = %s, offset = %s", result.toString(), result.getRecordMetadata().partition(), result.getRecordMetadata().offset()));
                }
            });
        }
        logger.info("Sending message to kafka cluster");
        kafkaTemplate.send(topic, message);
    }
}
