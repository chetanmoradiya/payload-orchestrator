package com.cloudtechies.orchestrator.schedular;

import com.cloudtechies.orchestrator.schedular.tasks.PayloadSplitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PayloadSplitterSchedular {

    @Autowired
    PayloadSplitter splitter;

    @Scheduled(fixedRate = 2000)
    public void runPayloadSplitter(){
        splitter.splitPayload();
    }
    


}
