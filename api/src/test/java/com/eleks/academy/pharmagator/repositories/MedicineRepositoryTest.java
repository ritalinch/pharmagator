package com.eleks.academy.pharmagator.repositories;

import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.projections.MedicineDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MedicineRepositoryTest {

    @Autowired
    private MedicineRepository medicineRepository;


    @Test
    @Rollback(value = false)
    @Order(1)
    void save_ok() {

        for (int i = 1; i <= 5; i++) {
            medicineRepository.save(new Medicine(0, "med_" + i));
        }

        List<Medicine> all = medicineRepository.findAll(Medicine.class);

        assertEquals(5, all.size());

        all.forEach(medicine -> {

            String title = medicine.getTitle();

            String[] s = title.split("_");

            String titleSuffix = s[1];

            int expectedId = Integer.parseInt(titleSuffix);

            assertEquals(expectedId, medicine.getId());

            assertTrue(title.startsWith("med_"));

            assertTrue(title.endsWith(titleSuffix));
        });
    }

    @Test
    void findById_existingEntityId_optionalWithValue() {

        Optional<MedicineDto> dtoOptional = medicineRepository.findById(1L, MedicineDto.class);

        assertTrue(dtoOptional.isPresent());

        MedicineDto medicineDto = dtoOptional.get();

        assertEquals("med_1", medicineDto.getTitle());
    }

    @Test
    void findById_nonExistingEntityId_emptyOptional() {

        Optional<MedicineDto> dtoOptional = medicineRepository.findById(100L, MedicineDto.class);

        assertTrue(dtoOptional.isEmpty());
    }

    @Test
    void save_validEntity_ok() {

        Medicine savedEntity = medicineRepository.save(new Medicine(0L, "med_6"));

        assertNotNull(savedEntity);
    }

    @Test
    void save_entityWithManuallyDefinedId_ok() {
        Medicine savedEntity = medicineRepository.save(new Medicine(100L, "med_6"));

        assertTrue(savedEntity.getId() != 100L);

        assertNotNull(savedEntity);
    }
}