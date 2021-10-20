package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.entities.requestEntities.PriceRequest;
import com.eleks.academy.pharmagator.repositories.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prices")
public class PriceController {
    private final PriceRepository priceRepository;

    @GetMapping
    public List<Price> getAll() {
        return priceRepository.findAll();
    }

    @GetMapping("/pharmacies/{pharmacyId}/medicines/{medicineId}")
    public ResponseEntity<Optional<Price>> getById(@PathVariable("medicineId") Long medicineId,
                                                   @PathVariable("pharmacyId") Long pharmacyId) {
        return ResponseEntity.ok(priceRepository.findAllByMedicineIdAndPharmacyId(medicineId, pharmacyId));
    }

    @PostMapping
    public void create(@Valid @RequestBody PriceRequest price) {
        this.priceRepository.save(new Price(
                null,
                null,
                price.getPrice(),
                price.getExternalId(),
                price.getUpdatedAt()
        ));
    }

    @DeleteMapping("/pharmacies/{pharmacyId}/medicines/{medicineId}")
    public ResponseEntity deleteById(@PathVariable("medicineId") Long medicineId,
                                     @PathVariable("pharmacyId") Long pharmacyId) {
        if (priceRepository.existsByMedicineIdAndPharmacyId(medicineId, pharmacyId)) {
            priceRepository.deleteByMedicineIdAndPharmacyId(medicineId, pharmacyId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/pharmacies/{pharmacyId}/medicines/{medicineId}")
    public ResponseEntity update(@PathVariable("medicineId") Long medicineId,
                                 @PathVariable("pharmacyId") Long pharmacyId,
                                 @Valid @RequestBody PriceRequest priceRequest) {
        return this.priceRepository
                .findAllByMedicineIdAndPharmacyId(medicineId, pharmacyId)
                .map(p -> {
                    p.setUpdatedAt(priceRequest.getUpdatedAt());
                    p.setPrice(priceRequest.getPrice());
                    p.setExternalId(priceRequest.getExternalId());
                    return ResponseEntity.ok(p);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
