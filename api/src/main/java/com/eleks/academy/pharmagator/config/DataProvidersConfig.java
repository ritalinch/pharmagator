package com.eleks.academy.pharmagator.config;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class DataProvidersConfig {

    @Value("${pharmagator.data-providers.apteka-ds.url}")
    private String pharmacyDSBaseUrl;

    @Value("${pharmagator.data-providers.pharmacy-anc.url}")
    private String pharmacyANCBaseUrl;

    @Value("${pharmagator.data-providers.apteka-liki24.url}")
    private String pharmacyLiki24BaseUrl;

    @Value("${pharmagator.data-providers.apteka-rozetka.url}")
    private String pharmacyRozetkaBaseUrl;

    @Value("${pharmagator.data-providers.aptslav.base-url}")
    private String aptslavBaseUrl;


    @Bean(name = "pharmacyDSWebClient")
    public WebClient pharmacyDSWebClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(pharmacyDSBaseUrl)
                .build();
    }

    @Bean(name = "pharmacyANCWebClient")
    public WebClient pharmacyANCWebClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(pharmacyANCBaseUrl)
                .build();
    }

    @Bean(name = "pharmacyLiki24WebClient")
    public WebClient pharmacyLiki24WebClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("x-lang", "uk")
                .baseUrl(pharmacyLiki24BaseUrl)
                .build();
    }

    @Bean(name = "pharmacyRozetkaWebClient")
    public WebClient pharmacyRozetkaWebClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(pharmacyRozetkaBaseUrl)
                .build();
    }

    @SneakyThrows
    @Bean(name = "aptslavWebClient")
    public WebClient aptslavWebClient() {
        /*
         * We had some issues with SSL certificate validation
         * @see <a href = "https://eleksjavacamp2021.slack.com/archives/C02FXNP9VL6/p1634309368128700"></a>
         * Unfortunately,I still haven`t figure out how to add key to Java keystore.
         * Therefore,we were forced to override SSL context,which is not a good practice.
         */

        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient
                .create()
                .secure(sslContextSpec -> sslContextSpec.sslContext(sslContext));

        ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(connector)
                .baseUrl(aptslavBaseUrl)
                .build();

    }

    @Bean
    public SpelAwareProxyProjectionFactory projectionFactory() {

        return new SpelAwareProxyProjectionFactory();
    }

}
