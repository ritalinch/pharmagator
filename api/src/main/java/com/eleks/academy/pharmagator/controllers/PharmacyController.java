package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pharmacies")
public class PharmacyController {
    private final PharmacyRepository pharmacyRepository;

    @GetMapping
    public ResponseEntity<List<Pharmacy>> getAll() {
        return ResponseEntity.ok(pharmacyRepository.findAll());
    }

    @GetMapping("/{pharmacyId}")
    public ResponseEntity<Optional<Pharmacy>> getById(@PathVariable Long pharmacyId) {
        return ResponseEntity.ok(pharmacyRepository.findById(pharmacyId));
    }

    @PostMapping
    public void create(@RequestParam Pharmacy pharmacy) {
        pharmacyRepository.save(pharmacy);
    }

    @DeleteMapping("/{pharmacyId}")
    public ResponseEntity deleteById(@PathVariable Long pharmacyId) {
        if (pharmacyRepository.existsById(pharmacyId)) {
            pharmacyRepository.deleteById(pharmacyId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{pharmacyId}")
    public ResponseEntity update(@PathVariable Long pharmacyId, @RequestParam Pharmacy pharmacy) {
        if (pharmacyRepository.existsById(pharmacyId)) {
            pharmacy.setId(pharmacyId);
            create(pharmacy);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
