package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.requestEntities.MedicineRequestDto;
import com.eleks.academy.pharmagator.responseEntity.MedicineResponseDto;
import com.eleks.academy.pharmagator.services.MedicineControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medicines")
public class MedicineController {

    private final MedicineControllerService medicineControllerService;

    @GetMapping
    public List<MedicineResponseDto> getAll() {
        return medicineControllerService.getAll();
    }

    @GetMapping("/{medicineId}")
    public ResponseEntity<MedicineResponseDto> getById(@PathVariable("id") Long medicineId) {
        return medicineControllerService.getById(medicineId);
    }

    @PostMapping
    public void create(@Valid @RequestBody MedicineRequestDto medicine) {
        medicineControllerService.create(medicine);
    }

    @DeleteMapping("/{medicineId}")
    public ResponseEntity deleteById(@PathVariable("id") Long medicineId) {
        return medicineControllerService.deleteById(medicineId);
    }

    @PutMapping("/{medicineId}")
    public ResponseEntity<MedicineResponseDto> update(@PathVariable("id") Long medicineId,
                                                      @Valid @RequestBody MedicineRequestDto medicine) {
        return medicineControllerService.update(medicineId, medicine);
    }
}
