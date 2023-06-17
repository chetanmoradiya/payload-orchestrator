package com.cloudtechies.orchestrator.route;

import com.cloudtechies.orchestrator.config.PollerProperties;
import com.cloudtechies.orchestrator.process.FileOrchestrator;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PollerRouteBuilder extends RouteBuilder {

    @Autowired
    PollerProperties pollerProperties;

    @Autowired
    FileOrchestrator fileOrchestrator;

    @Override
    public void configure(){

        log.info("Configuring camel route for file ingestion.");

        String options = String.format(
                "move=.staging" +
                        "&recursive=true"+
                        "&sortBy=ignoreCase:file:modified;file:name"+
                        "&include="+ pollerProperties.getRegex()+
                        "&delay="+ pollerProperties.getDelay()
        );

        String fromPath = String.format("%s?%s", pollerProperties.getIngestionDir(),options);

        from("file:"+ fromPath)
                .routeId("IngestionRouteBuilder")
                    .log("Processing file: ${file:name}")
                    .bean(fileOrchestrator)
                .end();

    }

}
