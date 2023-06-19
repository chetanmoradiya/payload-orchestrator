package com.cloudtechies.orchestrator.schedular;

import com.cloudtechies.orchestrator.entity.Payload;
import com.cloudtechies.orchestrator.enums.PayloadState;
import com.cloudtechies.orchestrator.repos.PayloadRepository;
import com.cloudtechies.orchestrator.repos.TransactionReportRepository;
import com.cloudtechies.orchestrator.schedular.tasks.RespOrchestrator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class RespCreationSchedular {

    @Autowired
    RespOrchestrator respOrchestrator;

    @Autowired
    PayloadRepository payloadRepository;

    @Scheduled(fixedRate = 5000)
    public void runPayloadSplitter(){

        List<Payload> inProcessPayloads = payloadRepository.findByPayloadStateOrderByUpdateTsDescCreateTsDesc(PayloadState.IN_PROCESS);
        for(Payload pyd : inProcessPayloads){
            respOrchestrator.checkAndCreateResponse(pyd);
        }

    }
    


}
