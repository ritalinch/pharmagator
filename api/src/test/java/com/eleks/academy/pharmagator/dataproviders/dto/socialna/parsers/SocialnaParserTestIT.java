package com.eleks.academy.pharmagator.dataproviders.dto.socialna.parsers;

import com.eleks.academy.pharmagator.dataproviders.dto.MedicineDto;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class SocialnaParserTestIT {

    private static MockWebServer mockWebServer;

    private SocialnaParser subject;

    private static final String BASE_URL = "https://1sa.com.ua/medikamenty/?p=1";

    private static final String PHARMACY_NAME = "apteka-911-socialna";

    @Value("classpath:resources/html/socialna-test-page.html")
    private String htmlTestPage;

    @BeforeAll
    static void beforeAll() {
        mockWebServer = new MockWebServer();

        mockWebServer.url(BASE_URL);
    }

    @AfterAll
    static void afterAll() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void setUp() {
        subject = new SocialnaParser();

        ReflectionTestUtils.setField(subject, "pharmacyName", PHARMACY_NAME);
    }

    @Test
    void getMedicinesFromPageByUrl_ok() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE)
                        .setBody(htmlTestPage)
        );

        Stream<MedicineDto> resultStream = subject.getMedicinesFromPageByUrl(BASE_URL);

        List<MedicineDto> resultDataSet = resultStream.toList();

        assertEquals(10, resultDataSet.size());

        resultDataSet.forEach(Assertions::assertNotNull);

        resultDataSet.forEach(medicineDto -> assertEquals(PHARMACY_NAME, medicineDto.getPharmacyName()));
    }

    @Test
    void getMedicinesFromPageByUrl_malformedUrl_IAE(){
        assertThrows(IllegalArgumentException.class, ()-> subject.getMedicinesFromPageByUrl("qwerty"));
    }
}