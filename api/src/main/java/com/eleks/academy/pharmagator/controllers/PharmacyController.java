package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.entities.requestEntities.PharmacyRequest;
import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pharmacies")
public class PharmacyController {
    private final PharmacyRepository pharmacyRepository;

    @GetMapping
    public List<Pharmacy> getAll() {
        return pharmacyRepository.findAll();
    }

    @GetMapping("/{pharmacyId}")
    public ResponseEntity<Optional<Pharmacy>> getById(@PathVariable("id") Long pharmacyId) {
        return ResponseEntity.ok(pharmacyRepository.findById(pharmacyId));
    }

    @PostMapping
    public void create(@Valid @RequestBody PharmacyRequest pharmacy) {
        pharmacyRepository.save(new Pharmacy(null, pharmacy.getName(), pharmacy.getMedicineLinkTemplate()));
    }

    @DeleteMapping("/{pharmacyId}")
    public ResponseEntity deleteById(@PathVariable("id") Long pharmacyId) {
        if (pharmacyRepository.existsById(pharmacyId)) {
            pharmacyRepository.deleteById(pharmacyId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{pharmacyId}")
    public ResponseEntity<Pharmacy> update(@PathVariable("id") Long pharmacyId,
                                           @Valid @RequestBody PharmacyRequest pharmacy) {
        return this.pharmacyRepository.findById(pharmacyId)
                .map(p -> {
                    p.setName(pharmacy.getName());
                    p.setMedicineLinkTemplate(pharmacy.getMedicineLinkTemplate());
                    return ResponseEntity.ok(p);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
