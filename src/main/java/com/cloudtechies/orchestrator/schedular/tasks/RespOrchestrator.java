package com.cloudtechies.orchestrator.schedular.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RespOrchestrator {

    public void checkAndCreateResponse(){
        log.info("Running Resp Creation thread...");
    }
}
