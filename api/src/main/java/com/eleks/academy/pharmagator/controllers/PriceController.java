package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.entities.PriceId;
import com.eleks.academy.pharmagator.requestEntities.PriceRequestDto;
import com.eleks.academy.pharmagator.responseEntity.PriceResponseDto;
import com.eleks.academy.pharmagator.services.PriceControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prices")
public class PriceController {

    private final PriceControllerService priceControllerService;

    @GetMapping
    public List<PriceResponseDto> getAll() {
        return priceControllerService.getAll();
    }

    @GetMapping("/pharmacies/{pharmacyId}/medicines/{medicineId}")
    public ResponseEntity<PriceResponseDto> getById(@PathVariable("medicineId") Long medicineId,
                                                   @PathVariable("pharmacyId") Long pharmacyId) {
        return priceControllerService.getById(new PriceId(medicineId, pharmacyId));
    }

    @PostMapping
    public void create(@Valid @RequestBody PriceRequestDto priceRequestDto) {
        priceControllerService.create(priceRequestDto);
    }

    @DeleteMapping("/pharmacies/{pharmacyId}/medicines/{medicineId}")
    public ResponseEntity deleteById(@PathVariable("medicineId") Long medicineId,
                                     @PathVariable("pharmacyId") Long pharmacyId) {
        return priceControllerService.deleteById(new PriceId(medicineId, pharmacyId));
    }

    @PutMapping("/pharmacies/{pharmacyId}/medicines/{medicineId}")
    public ResponseEntity<PriceResponseDto> update(@PathVariable("medicineId") Long medicineId,
                                 @PathVariable("pharmacyId") Long pharmacyId,
                                 @Valid @RequestBody PriceRequestDto priceRequestDto) {
        return priceControllerService.update(new PriceId(medicineId, pharmacyId), priceRequestDto);
    }
}
