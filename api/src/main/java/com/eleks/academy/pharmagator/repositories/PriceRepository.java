package com.eleks.academy.pharmagator.repositories;

import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.entities.PriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, PriceId> {

    Optional<Price> findAllByMedicineIdAndPharmacyId(long medicineId, long pharmacyId);

    boolean existsByMedicineIdAndPharmacyId(long medicineId, long pharmacyId);

    void deleteByMedicineIdAndPharmacyId(long medicineId, long pharmacyId);

}
