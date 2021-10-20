package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.repositories.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/prices")
public class PriceController {
    private final PriceRepository priceRepository;

    @GetMapping
    public ResponseEntity<List<Price>> getAll() {
        return ResponseEntity.ok(priceRepository.findAll());
    }

    @GetMapping("/pharmacies/{pharmacyId}/medicines/{medicineId}")
    public ResponseEntity<Optional<Price>> getById(@PathVariable Long medicineId, @PathVariable Long pharmacyId) {
        return ResponseEntity.ok(priceRepository.findAllByMedicineIdAndPharmacyId(medicineId, pharmacyId));
    }

    @PostMapping
    public void create(@RequestParam Price price) {
        priceRepository.save(price);
    }

    @DeleteMapping("/pharmacies/{pharmacyId}/medicines/{medicineId}")
    public ResponseEntity deleteById(@PathVariable Long medicineId, @PathVariable Long pharmacyId) {
        if (priceRepository.existsByMedicineIdAndPharmacyId(medicineId, pharmacyId)) {
            priceRepository.deleteByMedicineIdAndPharmacyId(medicineId, pharmacyId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/pharmacies/{pharmacyId}/medicines/{medicineId}")
    public ResponseEntity update(@PathVariable Long medicineId, @PathVariable Long pharmacyId, @RequestParam Price price) {
        if (priceRepository.existsByMedicineIdAndPharmacyId(medicineId, pharmacyId)) {
            price.setMedicineId(medicineId);
            price.setPharmacyId(pharmacyId);
            create(price);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
