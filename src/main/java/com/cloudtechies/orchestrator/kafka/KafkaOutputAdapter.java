package com.cloudtechies.orchestrator.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class KafkaOutputAdapter {

    @Autowired
    KafkaProducer kafkaProducer;

    public void sendMsgToKafka(String msg, String topic, String payloadId, Long payloadTs){
        HashMap<String, Object> headerMap = new HashMap<>();
        headerMap.put("PAYLOAD_ID",payloadId);
        headerMap.put("PAYLOAD_TS", payloadTs);
        kafkaProducer.send(topic,msg,"key",headerMap);
    }
}
