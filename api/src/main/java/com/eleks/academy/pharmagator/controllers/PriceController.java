package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.entities.PriceId;
import com.eleks.academy.pharmagator.repositories.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/prices")
public class PriceController {

    private final PriceRepository priceRepository;

    @GetMapping
    public ResponseEntity<List<Price>> getAll() {
        return ResponseEntity.ok(priceRepository.findAll());
    }

    @GetMapping(value = "/{priceId}")
    public @ResponseBody ResponseEntity<Price> getById(@PathVariable PriceId priceId) {
        return ResponseEntity.of(priceRepository.findByPharmacyIdAndMedicineId(priceId.getPharmacyId(), priceId.getMedicineId()));
    }

    @DeleteMapping(value = "/{priceId}")
    public @ResponseBody ResponseEntity.HeadersBuilder<?> deleteById(@PathVariable PriceId priceId) {
        if (priceRepository.findByPharmacyIdAndMedicineId(priceId.getPharmacyId(), priceId.getMedicineId()).isPresent()) {
            priceRepository.deleteByPharmacyIdAndMedicineId(priceId.getPharmacyId(), priceId.getMedicineId());
            return ResponseEntity.noContent();
        } else {
            return ResponseEntity.notFound();
        }
    }

    @PatchMapping(value = "/{priceId}")
    public @ResponseBody ResponseEntity.BodyBuilder update(@PathVariable Price priceId, @RequestBody Price price) {
        if (priceRepository.findByPharmacyIdAndMedicineId(priceId.getPharmacyId(), priceId.getMedicineId()).isPresent()) {
            price.setMedicineId(priceId.getMedicineId());
            price.setPharmacyId(priceId.getPharmacyId());
            priceRepository.save(price);
            return ResponseEntity.ok();
        } else {
            return ResponseEntity.badRequest();
        }
    }

    @PostMapping(value = "/")
    public @ResponseBody ResponseEntity<Price> create(@RequestBody Price price) {
        return ResponseEntity.ok(priceRepository.save(price));
    }
}
