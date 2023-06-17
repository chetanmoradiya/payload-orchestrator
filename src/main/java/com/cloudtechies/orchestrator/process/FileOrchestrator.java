package com.cloudtechies.orchestrator.process;

import com.cloudtechies.orchestrator.entity.Payload;
import com.cloudtechies.orchestrator.enums.PayloadState;
import com.cloudtechies.orchestrator.repos.PayloadRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Slf4j
public class FileOrchestrator {

    @Autowired
    PayloadRepository payloadRepository;

    public void createPayload(Exchange exchange){

        String fileName = (String) exchange.getIn().getHeader("CamelFileNameOnly");
        String absolutePath = (String) exchange.getIn().getHeader("CamelFileAbsolutePath");
        Long fileLength = (Long) exchange.getIn().getHeader("CamelFileLength");
        Long lastModified = (Long) exchange.getIn().getHeader("CamelFileLastModified");
        String parentPath = (String) exchange.getIn().getHeader("CamelFileParent");

        Payload payload = Payload.builder()
                .createTs(LocalDateTime.now())
                .payloadId(UUID.randomUUID())
                .absolutePath(absolutePath)
                .fileName(fileName)
                .payloadState(PayloadState.POLLED)
                .build();

        /*
        TODO
        Apply Idempotency - if same name and last modified timestamp file comes again then do not create a new one but update the
        existing one (if any) with status POLLED and update_ts = null
         */
        payloadRepository.save(payload);
        log.info("Payload - {} created for file - {} with status POLLED",payload.getPayloadId(),absolutePath);
    }

}
