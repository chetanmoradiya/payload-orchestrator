package com.cloudtechies.orchestrator.schedular.tasks;

import com.cloudtechies.orchestrator.config.KafkaProps;
import com.cloudtechies.orchestrator.entity.Payload;
import com.cloudtechies.orchestrator.enums.PayloadState;
import com.cloudtechies.orchestrator.enums.TransactionStatus;
import com.cloudtechies.orchestrator.exception.UnrecoverableException;
import com.cloudtechies.orchestrator.kafka.KafkaOutputAdapter;
import com.cloudtechies.orchestrator.repos.PayloadRepository;
import com.cloudtechies.orchestrator.repos.TransactionReportRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Component
@Slf4j
public class RespOrchestrator {

    @Autowired
    TransactionReportRepository transactionReportRepository;

    @Autowired
    PayloadRepository payloadRepository;

    @Autowired
    KafkaOutputAdapter kafkaOutputAdapter;

    @Autowired
    KafkaProps kafkaProps;

    @Transactional
    public void checkAndCreateResponse(Payload p){
        if(p.getInstructionCount() == transactionReportRepository.countByPayloadIdAndTxnStatusIn(p.getPayloadId(), Arrays.asList(TransactionStatus.ACPT,TransactionStatus.RJCT))){
            log.info("Going to create resp payload msg for payload [{}] with count {}",p.getPayloadId().toString(),p.getInstructionCount());
            p.setRespFileName(createRespFileName(p.getFileName()));
            p.setPayloadState(PayloadState.RESP_PENDING);
            payloadRepository.save(p);
            ObjectMapper mapper = new ObjectMapper();
            String kafkaMsg = "";
            try{
                kafkaMsg = mapper.writeValueAsString(p);
            }catch (JsonProcessingException e){
                throw new UnrecoverableException("JSONPROCESS",e.getMessage());
            }
            kafkaOutputAdapter.sendMsgToKafka(kafkaMsg,kafkaProps.getRespTopic(),p.getPayloadId().toString(),p.getCreateTs().toEpochMilli());
        }
    }

    private String createRespFileName(String submittedFileName){
        return String.format("%s_%s_%s.csv","RESP",submittedFileName.split("\\.")[0],createTimeStamp());

    }

    private String createTimeStamp(){
        Instant now = Instant.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuuMMdd_HHmmss");
        return LocalDateTime.now().format(dtf);
    }
}
