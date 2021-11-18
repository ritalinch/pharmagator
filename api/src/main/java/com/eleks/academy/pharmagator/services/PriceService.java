package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.dataproviders.dto.input.PriceDto;
import com.eleks.academy.pharmagator.entities.Price;

import java.util.List;
import java.util.Optional;

public interface PriceService {

    List<Price> findAll();

    Optional<Price> findById(Long pharmacyId, Long medicineId);

    Optional<Price> update(Long pharmacyId, Long medicineId, PriceDto priceDto);

<<<<<<< HEAD
<<<<<<< HEAD
    void deleteById(Long pharmacyId, Long medicineId);
    
=======
    @Override
    public void create(PriceRequestDto priceRequestDto) {
        this.priceRepository.save(priceRequestDto.getMappedEntity());
    }

    @Override
    public void deleteById(PriceId id) {
        if (priceRepository.existsByMedicineIdAndPharmacyId(id.getMedicineId(), id.getPharmacyId())) {
            priceRepository.deleteByMedicineIdAndPharmacyId(id.getMedicineId(), id.getPharmacyId());
        }
    }

    @Override
    public ResponseEntity<PriceResponseDto> update(PriceId id, PriceRequestDto entity) {
        return this.priceRepository.findAllByMedicineIdAndPharmacyId(id.getMedicineId(), id.getPharmacyId())
                .map(p -> ResponseEntity.ok(entity.getMappedDtoEntity()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
>>>>>>> 6efd804 (Fix "delete" methods in controllers and services)
}
=======
    void deleteById(Long pharmacyId, Long medicineId);

}
>>>>>>> 77f3be9 (Update)
