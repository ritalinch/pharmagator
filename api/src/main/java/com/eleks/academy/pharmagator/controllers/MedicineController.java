package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.requestEntities.MedicineRequestDto;
import com.eleks.academy.pharmagator.responseEntity.MedicineResponseDto;
import com.eleks.academy.pharmagator.services.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medicines")
public class MedicineController {

    private final MedicineService medicineService;

    @GetMapping
    public List<MedicineResponseDto> getAll() {
        return medicineService.getAll();
    }

    @GetMapping("/{medicineId}")
    public ResponseEntity<MedicineResponseDto> getById(@PathVariable Long medicineId) {
        return medicineService.getById(medicineId);
    }

    @PostMapping
    public void create(@Valid @RequestBody MedicineRequestDto medicine) {
        medicineService.create(medicine);
    }

    @DeleteMapping("/{medicineId}")
    public void deleteById(@PathVariable Long medicineId) {
        medicineService.deleteById(medicineId);
    }

    @PutMapping("/{medicineId}")
    public ResponseEntity<MedicineResponseDto> update(@PathVariable Long medicineId,
                                                      @Valid @RequestBody MedicineRequestDto medicine) {
        return medicineService.update(medicineId, medicine);
    }
}
