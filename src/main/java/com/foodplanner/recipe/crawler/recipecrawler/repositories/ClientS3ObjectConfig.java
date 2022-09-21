package com.foodplanner.recipe.crawler.recipecrawler.repositories;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "clients3configs")
public class ClientS3ObjectConfig {
    private Map<String, S3ObjectConfig> clientS3ConfigsMap;

    public Map<String, S3ObjectConfig> getClientS3ConfigsMap() {
        return clientS3ConfigsMap;
    }

    public void setClientS3ConfigsMap(
            Map<String, S3ObjectConfig> clientS3ConfigsMap) {
        this.clientS3ConfigsMap = clientS3ConfigsMap;
    }
}
