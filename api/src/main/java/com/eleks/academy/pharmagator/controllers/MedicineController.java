package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.repositories.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/medicines")
public class MedicineController {

    private final MedicineRepository medicineRepository;

    @GetMapping
    public ResponseEntity<List<Medicine>> getAll() {
        return ResponseEntity.ok(medicineRepository.findAll());
    }

    @GetMapping(value = "/{medicineId}")
    public @ResponseBody ResponseEntity<Medicine> getById(@PathVariable Integer medicineId) {
        return ResponseEntity.of(medicineRepository.findById(Long.valueOf(medicineId)));
    }

    @DeleteMapping(value = "/{medicineId}")
    public @ResponseBody ResponseEntity.BodyBuilder deleteById(@PathVariable Integer medicineId) {
        if (medicineRepository.existsById(Long.valueOf(medicineId))) {
            medicineRepository.deleteById(Long.valueOf(medicineId));
            return ResponseEntity.ok();
        } else {
            return ResponseEntity.badRequest();
        }
    }

    @PatchMapping(value = "/{medicineId}")
    public @ResponseBody ResponseEntity.BodyBuilder update(@PathVariable Integer medicineId, @RequestBody Medicine medicine) {
        if (medicineRepository.existsById(Long.valueOf(medicineId))) {
            medicine.setId(medicineId);
            medicineRepository.save(medicine);
            return ResponseEntity.ok();
        } else {
            return ResponseEntity.badRequest();
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity<Medicine> create(@RequestBody Medicine medicine) {
        return ResponseEntity.ok(medicineRepository.save(medicine));
    }
}
