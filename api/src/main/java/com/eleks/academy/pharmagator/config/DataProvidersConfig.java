package com.eleks.academy.pharmagator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class DataProvidersConfig {

    @Value("${pharmagator.data-providers.apteka-ds.url}")
    private String pharmacyDSBaseUrl;

    @Value("${pharmagator.data-providers.anc.url}")
    private String pharmacyAncBaseUrl;

    @Bean(name = "pharmacyDSWebClient")
    public WebClient pharmacyDSWebClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(pharmacyDSBaseUrl)
                .build();
    }

    @Bean(name = "pharmacyAncWebClient")
    public WebClient pharmacyAncWebClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(pharmacyAncBaseUrl)
                .build();
    }

}
