package com.cloudtechies.orchestrator.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "filepoller")
@EnableConfigurationProperties(PollerProperties.class)
@Data
public class PollerProperties {

    private String regex;

    private String ingestionDir;

    private String delay;

}
