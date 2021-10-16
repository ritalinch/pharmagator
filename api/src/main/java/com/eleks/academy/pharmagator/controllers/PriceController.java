package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.entities.PriceId;
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
    public @ResponseBody ResponseEntity<List<Price>> getAll() {
        return ResponseEntity.ok(priceRepository.findAll());
    }

    @GetMapping("/{priceId}")
    public @ResponseBody ResponseEntity<Optional<Price>> getById(@PathVariable PriceId priceId) {
        return ResponseEntity.ok(priceRepository.findAllByMedicineIdAndPharmacyId(priceId.getMedicineId(), priceId.getPharmacyId()));
    }

    @PostMapping
    public void create(@RequestParam Price price) {
        priceRepository.save(price);
    }

    @DeleteMapping("/{priceId}")
    public @ResponseBody ResponseEntity.BodyBuilder deleteById(@PathVariable PriceId priceId) {
        if (priceRepository.existsByMedicineIdAndPharmacyId(priceId.getMedicineId(), priceId.getPharmacyId())) {
            priceRepository.deleteByMedicineIdAndPharmacyId(priceId.getMedicineId(), priceId.getPharmacyId());
            return ResponseEntity.ok();
        } else {
            return ResponseEntity.badRequest();
        }
    }

    @PatchMapping("/{priceId}")
    public @ResponseBody ResponseEntity.BodyBuilder update(@PathVariable PriceId priceId, @RequestParam Price price) {
        if (priceRepository.existsByMedicineIdAndPharmacyId(priceId.getMedicineId(), price.getPharmacyId())) {
            price.setMedicineId(priceId.getMedicineId());
            price.setPharmacyId(priceId.getPharmacyId());
            create(price);
            return ResponseEntity.ok();
        } else {
            return ResponseEntity.badRequest();
        }
    }
}
