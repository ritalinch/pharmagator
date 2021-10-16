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
    public @ResponseBody ResponseEntity<List<Medicine>> getAll() {
        return ResponseEntity.ok(medicineRepository.findAll());
    }

    @GetMapping("/{medicineId}")
    public @ResponseBody ResponseEntity<Optional<Medicine>> getById(@PathVariable int medicineId) {
        return ResponseEntity.ok(medicineRepository.findById((long) medicineId));
    }

    @PostMapping
    public void create(@RequestParam Medicine medicine) {
        medicineRepository.save(medicine);
    }

    @DeleteMapping("/{medicineId}")
    public @ResponseBody ResponseEntity.BodyBuilder deleteById(@PathVariable int medicineId) {
        if (medicineRepository.existsById((long)medicineId)) {
            medicineRepository.deleteById((long)medicineId);
            return ResponseEntity.ok();
        } else {
            return ResponseEntity.badRequest();
        }
    }

    @PatchMapping("/{medicineId}")
    public @ResponseBody ResponseEntity.BodyBuilder update(@PathVariable int medicineId, @RequestParam Medicine medicine) {
        if (medicineRepository.existsById((long) medicineId)) {
            medicine.setId(medicineId);
            create(medicine);
            return ResponseEntity.ok();
        } else {
            return ResponseEntity.badRequest();
        }
    }
}
