package com.eleks.academy.pharmagator.repositories;

import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.projections.PriceDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PriceRepositoryTest {

    @Autowired
    private PriceRepository subject;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    void save_validEntity_ok() {
        Pharmacy pharmacy = pharmacyRepository
                .saveAndFlush(new Pharmacy(0L, "Pharmacy", "Link"));

        Medicine medicine = medicineRepository.saveAndFlush(new Medicine(0L, "med"));

        Price price = new Price(pharmacy.getId(), medicine.getId(), BigDecimal.TEN,
                "extId", Instant.now());

        subject.save(price);
    }

    @Test
    void save_entityWithInvalidPharmacyId_DataIntegrityExceptionThrown() {

        Medicine medicine = new Medicine(0L, "medicine");

        medicineRepository.saveAndFlush(medicine);

        Price price = new Price(100L, medicine.getId(), BigDecimal.TEN,
                "extId", Instant.now());

        assertThrows(DataIntegrityViolationException.class, () -> subject.save(price));
    }

    @Test
    void save_entityWithInvalidMedicineId_DataIntegrityExceptionThrown() {
        Pharmacy pharmacy = new Pharmacy(0L, "new pharmacy", "link");

        pharmacyRepository.saveAndFlush(pharmacy);

        Price price = new Price(pharmacy.getId(), 100L, BigDecimal.TEN,
                "extId", Instant.now());

        assertThrows(DataIntegrityViolationException.class, () -> subject.save(price));
    }

    @Test
    void findAll_ok() {

        List<PriceDto> all = subject.findAll(PriceDto.class);
        System.out.println(all);
        assertEquals(1, all.size());

    }

    @Test
    void findById_existingEntityId_optionalPresent() {

        Optional<Price> optionalPrice = subject
                .findByMedicineIdAndPharmacyId(1L, 2L, Price.class);

        assertTrue(optionalPrice.isPresent());

        Price price = optionalPrice.get();

        assertEquals(1L, price.getMedicineId());

        assertEquals(2L, price.getPharmacyId());

        BigDecimal expected = BigDecimal.TEN
                .setScale(2, RoundingMode.HALF_UP);

        assertEquals(expected, price.getPrice());
    }

    @Test
    void findById_nonExistingEntityId_optionalEmpty() {

        Optional<Price> optionalPrice = subject
                .findByMedicineIdAndPharmacyId(1L, 123L, Price.class);

        assertTrue(optionalPrice.isEmpty());
    }
}