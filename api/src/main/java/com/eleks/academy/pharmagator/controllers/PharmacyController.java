package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.requestEntities.PharmacyRequestDto;
import com.eleks.academy.pharmagator.responseEntity.PharmacyResponseDto;
import com.eleks.academy.pharmagator.services.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pharmacies")
public class PharmacyController {

    private final PharmacyService pharmacyService;

    @GetMapping
    public List<PharmacyResponseDto> getAll() {
        return pharmacyService.getAll();
    }

    @GetMapping("/{pharmacyId}")
    public ResponseEntity<PharmacyResponseDto> getById(@PathVariable("id") Long pharmacyId) {
        return pharmacyService.getById(pharmacyId);

    }

    @PostMapping
    public void create(@Valid @RequestBody PharmacyRequestDto pharmacyRequestDto) {
        pharmacyService.create(pharmacyRequestDto);
    }

    @DeleteMapping("/{pharmacyId}")
    public ResponseEntity deleteById(@PathVariable("id") Long pharmacyId) {
        return pharmacyService.deleteById(pharmacyId);
    }

    @PutMapping("/{pharmacyId}")
    public ResponseEntity<PharmacyResponseDto> update(@PathVariable("id") Long pharmacyId,
                                                      @Valid @RequestBody PharmacyRequestDto pharmacyRequestDto) {
        return pharmacyService.update(pharmacyId, pharmacyRequestDto);
    }
}
