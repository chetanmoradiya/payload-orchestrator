package com.cloudtechies.orchestrator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka")
@EnableConfigurationProperties(KafkaProps.class)
@Data
public class KafkaProps {

    private String clusterUrl;

    private String outputTopic;

}
