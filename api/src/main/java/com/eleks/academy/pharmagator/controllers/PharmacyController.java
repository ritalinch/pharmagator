package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.controllers.requests.PharmacyRequest;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.projections.PharmacyDto;
import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import com.eleks.academy.pharmagator.services.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pharmacies")
public class PharmacyController {

    private final PharmacyService pharmacyService;

    @GetMapping
    public List<PharmacyDto> getAll() {

        return pharmacyService.findAll();
    }

    @GetMapping("/{pharmacyId}")
    public PharmacyDto getById(@PathVariable Long pharmacyId) {

        return pharmacyService.findById(pharmacyId);
    }

    @PostMapping("/")
    public PharmacyDto create(@Valid @RequestBody PharmacyRequest requestBody) {

        return pharmacyService.save(requestBody);
    }


    @DeleteMapping("/{pharmacyId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long pharmacyId) {

        pharmacyService.delete(pharmacyId);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{pharmacyId}")
    public PharmacyDto update(@PathVariable Long pharmacyId,
                              @RequestBody PharmacyRequest requestBody) {

        return pharmacyService.update(pharmacyId, requestBody);
    }
}
