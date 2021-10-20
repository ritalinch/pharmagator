package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.repositories.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/medicines")
public class MedicineController {
    private final MedicineRepository medicineRepository;

    @GetMapping
    public ResponseEntity<List<Medicine>> getAll() {
        return ResponseEntity.ok(medicineRepository.findAll());
    }

    @GetMapping("/{medicineId}")
    public ResponseEntity<Optional<Medicine>> getById(@PathVariable Long medicineId) {
        return ResponseEntity.ok(medicineRepository.findById(medicineId));
    }

    @PostMapping
    public void create(@RequestParam Medicine medicine) {
        medicineRepository.save(medicine);
    }

    @DeleteMapping("/{medicineId}")
    public ResponseEntity deleteById(@PathVariable Long medicineId) {
        if (medicineRepository.existsById(medicineId)) {
            medicineRepository.deleteById(medicineId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{medicineId}")
    public ResponseEntity update(@PathVariable Long medicineId, @RequestParam Medicine medicine) {
        if (medicineRepository.existsById(medicineId)) {
            medicine.setId(medicineId);
            create(medicine);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
