package com.eleks.academy.pharmagator;

import com.eleks.academy.pharmagator.dataproviders.aptslav.AptslavDataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@ActiveProfiles("test")
class PharmagatorApplicationTests {
    @Autowired
    private AptslavDataProvider aptslavDataProvider;
    @Test
    void contextLoads() {
    }
    @Test
    void savePharmacyTest(){

    }
}
