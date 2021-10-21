package com.eleks.academy.pharmagator.repositories;

import com.eleks.academy.pharmagator.entities.Pharmacy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class PharmacyRepositoryTest {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Value("${pharmagator.data-providers.aptslav.title}")
    private String title;

    @Value("${pharmagator.data-providers.aptslav.medicineLinkTemplate}")
    private String linkTemplate;

    @Test
    void v1_0_2_migration_test() {

        List<Pharmacy> all = pharmacyRepository.findAll();

        assertEquals(1, all.size());

        assertEquals(title, all.get(0).getName());

        assertEquals(linkTemplate, all.get(0).getMedicineLinkTemplate());
    }

    @Test
    void findByName_existingEntityName_ok() {

        Optional<Pharmacy> byName = pharmacyRepository.findByName(title);

        assertTrue(byName.isPresent());

        Pharmacy pharmacy = byName.get();

        assertEquals(title, pharmacy.getName());

        assertEquals(linkTemplate, pharmacy.getMedicineLinkTemplate());
    }

    @Test
    void findByName_nonExistingEntityName_emptyOptional() {

        Optional<Pharmacy> optional = pharmacyRepository.findByName("some name");

        assertTrue(optional.isEmpty());
    }

    @Test
    void findById_existingEntityId_ok() {

        Optional<Pharmacy> byId = pharmacyRepository.findById(1L);

        assertTrue(byId.isPresent());

        Pharmacy pharmacy = byId.get();

        assertEquals(title, pharmacy.getName());

        assertEquals(linkTemplate, pharmacy.getMedicineLinkTemplate());
    }

    @Test
    void findById_nonExistingEntityId_emptyOptional(){

        Optional<Pharmacy> optional = pharmacyRepository.findById(12L);

        assertTrue(optional.isEmpty());
    }
}