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
    public @ResponseBody ResponseEntity<List<Pharmacy>> getAll() {
        return ResponseEntity.ok(pharmacyRepository.findAll());
    }

    @GetMapping("/{pharmacyId}")
    public @ResponseBody ResponseEntity<Optional<Pharmacy>> getById(@PathVariable int pharmacyId) {
        return ResponseEntity.ok(pharmacyRepository.findById((long) pharmacyId));
    }

    @PostMapping
    public void create(@RequestParam Pharmacy pharmacy) {
        pharmacyRepository.save(pharmacy);
    }

    @DeleteMapping("/{pharmacyId}")
    public @ResponseBody ResponseEntity.BodyBuilder deleteById(@PathVariable int pharmacyId) {
        if (pharmacyRepository.existsById((long)pharmacyId)) {
            pharmacyRepository.deleteById((long)pharmacyId);
            return ResponseEntity.ok();
        } else {
            return ResponseEntity.badRequest();
        }
    }

    @PatchMapping("/{pharmacyId}")
    public @ResponseBody ResponseEntity.BodyBuilder update(@PathVariable int pharmacyId, @RequestParam Pharmacy pharmacy) {
        if (pharmacyRepository.existsById((long) pharmacyId)) {
            pharmacy.setId(pharmacyId);
            create(pharmacy);
            return ResponseEntity.ok();
        } else {
            return ResponseEntity.badRequest();
        }
    }
}
