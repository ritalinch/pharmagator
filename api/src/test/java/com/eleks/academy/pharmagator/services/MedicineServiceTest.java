package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.requestEntities.MedicineRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class MedicineServiceTest {

    @Autowired
    MedicineService service;

    MedicineRequestDto sampleDto;

    @BeforeEach
    public void setUp() {
        sampleDto = new MedicineRequestDto("some medicine");
    }

    @Test
    public void simpleTest() {
        service.create(sampleDto);

        Assertions.assertTrue(service.existsByTitle(sampleDto.getTitle()));
    }

    @Test
    public void test_allValuesAreDistinct() {
        for(int i = 0; i < 5; i++) {
            service.create(sampleDto);
        }

        final var amount = service.getAllByTitle(sampleDto.getTitle());

        Assertions.assertEquals(1, amount);
    }
}
