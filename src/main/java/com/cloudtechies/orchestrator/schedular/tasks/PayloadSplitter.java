package com.cloudtechies.orchestrator.schedular.tasks;

import com.cloudtechies.orchestrator.entity.Payload;
import com.cloudtechies.orchestrator.enums.PayloadState;
import com.cloudtechies.orchestrator.repos.PayloadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class PayloadSplitter {

    @Autowired
    PayloadRepository repository;
    
    public void splitPayload(){
        log.info("Running...");
        List<Payload> eligiblePayloads = repository.findByPayloadStateOrderByUpdateTsDescCreateTsDesc(PayloadState.TO_PROCESS);
        log.info("{} to process.",eligiblePayloads.size());
    }

}
