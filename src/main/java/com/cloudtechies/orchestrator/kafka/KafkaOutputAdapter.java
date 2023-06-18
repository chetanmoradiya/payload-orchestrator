package com.cloudtechies.orchestrator.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class KafkaOutputAdapter {

    @Autowired
    KafkaProducer kafkaProducer;

    public void sendMsgToKafka(String msg, String topic){
        kafkaProducer.send(topic,msg,"key",new HashMap<>());
    }
}
