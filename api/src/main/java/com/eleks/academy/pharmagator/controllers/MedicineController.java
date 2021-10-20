package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.entities.requestEntities.MedicineRequest;
import com.eleks.academy.pharmagator.repositories.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medicines")
public class MedicineController {
    private final MedicineRepository medicineRepository;

    @GetMapping
    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }

    @GetMapping("/{medicineId}")
    public ResponseEntity<Optional<Medicine>> getById(@PathVariable("id") Long medicineId) {
        return ResponseEntity.ok(medicineRepository.findById(medicineId));
    }

    @PostMapping
    public void create(@Valid @RequestBody MedicineRequest medicine) {
        medicineRepository.save(new Medicine(null, medicine.getTitle()));
    }

    @DeleteMapping("/{medicineId}")
    public ResponseEntity deleteById(@PathVariable("id") Long medicineId) {
        if (medicineRepository.existsById(medicineId)) {
            medicineRepository.deleteById(medicineId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{medicineId}")
    public ResponseEntity update(@PathVariable("id") Long medicineId,
                                 @Valid @RequestBody MedicineRequest medicine) {
        return this.medicineRepository.findById(medicineId)
                .map(p -> {
                    p.setTitle(medicine.getTitle());
                    return ResponseEntity.ok(p);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
