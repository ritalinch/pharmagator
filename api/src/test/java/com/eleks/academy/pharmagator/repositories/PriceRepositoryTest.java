package com.eleks.academy.pharmagator.repositories;

import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.projections.PriceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PriceRepositoryTest {
    private static boolean setUpDone = false;

    @Autowired
    private PriceRepository subject;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @BeforeEach
    @Rollback(value = false)
    void setUp() {
        if (!setUpDone) {

            Pharmacy pharmacy = pharmacyRepository
                    .save(new Pharmacy(0L, "Pharmacy", "Link"));

            Medicine medicine = medicineRepository.save(new Medicine(0L, "med"));


            Price price = new Price(pharmacy.getId(), medicine.getId(), BigDecimal.TEN,
                    "extId", Instant.now());

            subject.save(price);
        }
        setUpDone = true;
    }

    @Test
    void findAll_ok() {

        List<PriceDto> all = subject.findAll(PriceDto.class);

        assertEquals(1, all.size());

    }

    @Test
    void findById_existingEntityId_optionalPresent() {

        Optional<Price> optionalPrice = subject
                .findByMedicineIdAndPharmacyId(1L, 1L, Price.class);

        assertTrue(optionalPrice.isPresent());

        Price price = optionalPrice.get();

        assertEquals(BigDecimal.TEN,price.getPrice());
    }

    @Test
    void findById_nonExistingEntityId_optionalEmpty(){

        Optional<Price> optionalPrice = subject
                .findByMedicineIdAndPharmacyId(1L, 123L, Price.class);

        assertTrue(optionalPrice.isEmpty());
    }
}