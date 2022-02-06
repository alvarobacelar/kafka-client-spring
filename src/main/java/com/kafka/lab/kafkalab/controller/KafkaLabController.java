package com.kafka.lab.kafkalab.controller;

import com.kafka.lab.kafkalab.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaLabController {

    private Producer producer;

    @Autowired
    KafkaLabController(Producer producer, Environment env) { this.producer = producer; }

    @PostMapping(value = "/publish")
    public void sendMessageToKafka(@RequestParam(value = "message", defaultValue = "teste de envio de mensagem") String message){
        String topic = "kafka-lab";
        this.producer.sendMessage(topic, message);
    }
}
