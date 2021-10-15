package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pharmacies")
public class PharmacyController {

    private final PharmacyRepository pharmacyRepository;

    @GetMapping
    public ResponseEntity<List<Pharmacy>> getAll() {
        return ResponseEntity.ok(pharmacyRepository.findAll());
    }

    @GetMapping(value = "/{pharmacyId}")
    public @ResponseBody ResponseEntity<Pharmacy> getById(@PathVariable Integer pharmacyId) {
        return ResponseEntity.of(pharmacyRepository.findById(Long.valueOf(pharmacyId)));
    }

    @DeleteMapping(value = "/{pharmacyId}")
    public @ResponseBody ResponseEntity.BodyBuilder deleteById(@PathVariable Integer pharmacyId) {
        if (pharmacyRepository.existsById(Long.valueOf(pharmacyId))) {
            pharmacyRepository.deleteById(Long.valueOf(pharmacyId));
            return ResponseEntity.ok();
        } else {
            return ResponseEntity.badRequest();
        }
    }

    @PatchMapping(value = "/{pharmacyId}")
    public @ResponseBody ResponseEntity.BodyBuilder update(@PathVariable Integer pharmacyId, @RequestBody Pharmacy pharmacy) {
        if (pharmacyRepository.existsById(Long.valueOf(pharmacyId))) {
            pharmacy.setId(Long.valueOf(pharmacyId));
            pharmacyRepository.save(pharmacy);
            return ResponseEntity.ok();
        } else {
            return ResponseEntity.badRequest();
        }
    }

    @PutMapping(value = "/{pharmacyId}")
    public ResponseEntity<Pharmacy> create(@RequestBody Pharmacy pharmacy) {
        return ResponseEntity.ok(pharmacyRepository.save(pharmacy));
    }
}
