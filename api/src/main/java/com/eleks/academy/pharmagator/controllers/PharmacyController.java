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

    @GetMapping("/getById")
    public ResponseEntity<Pharmacy> getById(@RequestParam Long id) {
        return ResponseEntity.of(pharmacyRepository.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Pharmacy> create(@RequestBody Pharmacy pharmacy) {
        return ResponseEntity.ok(pharmacyRepository.save(pharmacy));
    }


    @DeleteMapping("/{pharmacyId}")
    public ResponseEntity<?> deleteById(@PathVariable Long pharmacyId) {
        Optional<Pharmacy> optionalPharmacy = pharmacyRepository.findById(pharmacyId);
        if (optionalPharmacy.isPresent()) {
            pharmacyRepository.deleteById(pharmacyId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{pharmacyId}")
    public ResponseEntity<Pharmacy> update(@PathVariable Long pharmacyId,@RequestBody Pharmacy pharmacy) {
        Optional<Pharmacy> optionalById = pharmacyRepository.findById(pharmacyId);
        if (optionalById.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            pharmacy.setId(pharmacyId);
            pharmacyRepository.save(pharmacy);
            return ResponseEntity.ok().build();
        }
    }
}
