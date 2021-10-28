package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.requestEntities.PharmacyRequestDto;
import com.eleks.academy.pharmagator.responseEntity.PharmacyResponseDto;
import com.eleks.academy.pharmagator.services.PharmacyControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pharmacies")
public class PharmacyController {

    private final PharmacyControllerService pharmacyControllerService;

    @GetMapping
    public List<PharmacyResponseDto> getAll() {
        return pharmacyControllerService.getAll();
    }

    @GetMapping("/{pharmacyId}")
    public ResponseEntity<PharmacyResponseDto> getById(@PathVariable("id") Long pharmacyId) {
        return pharmacyControllerService.getById(pharmacyId);

    }

    @PostMapping
    public void create(@Valid @RequestBody PharmacyRequestDto pharmacyRequestDto) {
        pharmacyControllerService.create(pharmacyRequestDto);
    }

    @DeleteMapping("/{pharmacyId}")
    public ResponseEntity deleteById(@PathVariable("id") Long pharmacyId) {
        return pharmacyControllerService.deleteById(pharmacyId);
    }

    @PutMapping("/{pharmacyId}")
    public ResponseEntity<PharmacyResponseDto> update(@PathVariable("id") Long pharmacyId,
                                                      @Valid @RequestBody PharmacyRequestDto pharmacyRequestDto) {
        return pharmacyControllerService.update(pharmacyId, pharmacyRequestDto);
    }
}
