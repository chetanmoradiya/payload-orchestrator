package com.cloudtechies.orchestrator.schedular;

import com.cloudtechies.orchestrator.schedular.tasks.RespOrchestrator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RespCreationSchedular {

    @Autowired
    RespOrchestrator respOrchestrator;

    @Scheduled(fixedRate = 5000)
    public void runPayloadSplitter(){
        respOrchestrator.checkAndCreateResponse();
    }
    


}
