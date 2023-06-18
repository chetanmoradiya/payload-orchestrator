package com.cloudtechies.orchestrator.schedular.tasks;

import com.cloudtechies.orchestrator.config.KafkaProps;
import com.cloudtechies.orchestrator.entity.Payload;
import com.cloudtechies.orchestrator.enums.PayloadState;
import com.cloudtechies.orchestrator.kafka.KafkaOutputAdapter;
import com.cloudtechies.orchestrator.repos.PayloadRepository;
import com.cloudtechies.orchestrator.util.Jsonifier;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.Instant;
import java.util.List;

@Component
@Slf4j
public class PayloadSplitter {

    @Autowired
    PayloadRepository repository;

    @Autowired
    KafkaOutputAdapter kafkaOutputAdapter;

    @Autowired
    KafkaProps kafkaProps;
    
    public void splitPayload() throws Exception{
        log.info("Running...");
        List<Payload> eligiblePayloads = repository.findByPayloadStateOrderByUpdateTsDescCreateTsDesc(PayloadState.TO_PROCESS);
        for(Payload pyd : eligiblePayloads){
            File file = new File(pyd.getAbsolutePath());
            splitAndJsonifyAndSendForward(file,pyd);
        }
        log.info("{} to process.",eligiblePayloads.size());
    }

    private void splitAndJsonifyAndSendForward(File file, Payload pyd){

        String headerLine = "";
        Long noOfTxns = 0L;

        try(BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()))){
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            for(CSVRecord csvRecord : csvParser){
                if(csvRecord.getRecordNumber()==1) {
                    headerLine = String.join(",",csvRecord.values());
                } else {
                    String msgToSend = Jsonifier.jsonify(String.join(",",csvRecord.values()),headerLine,",");
                    kafkaOutputAdapter.sendMsgToKafka(msgToSend, kafkaProps.getOutputTopic());
                    noOfTxns++;
                    log.info("Sent message to kafka.");
                }
            }
        }catch (Exception e){
            log.error("Exception - {}",e.getMessage());
        }

        updatePayload(pyd, PayloadState.IN_PROCESS,noOfTxns);

    }

    private void updatePayload(Payload p , PayloadState ps, Long noOfTxns){
        p.setUpdateTs(Instant.now());
        p.setInstructionCount(noOfTxns);
        p.setPayloadState(ps);
        repository.save(p);
        log.info("Payload Marked {} - count of txns - {}",ps,noOfTxns);
    }

}
