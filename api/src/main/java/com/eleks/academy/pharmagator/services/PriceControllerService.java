package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.entities.PriceId;
import com.eleks.academy.pharmagator.repositories.PriceRepository;
import com.eleks.academy.pharmagator.requestEntities.PriceRequestDto;
import com.eleks.academy.pharmagator.responseEntity.PriceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriceControllerService implements ControllerService<PriceId, PriceRequestDto, PriceResponseDto> {

    private final PriceRepository priceRepository;

    @Override
    public List<PriceResponseDto> getAll() {
        return priceRepository
                .findAll()
                .stream()
                .map(PriceResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<PriceResponseDto> getById(PriceId id) {
        return priceRepository.findAllByMedicineIdAndPharmacyId(id.getMedicineId(), id.getPharmacyId())
                .map(price -> ResponseEntity.ok(new PriceResponseDto(price)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public void create(PriceRequestDto priceRequestDto) {
        this.priceRepository.save(priceRequestDto.getMappedEntity());
    }

    @Override
    public ResponseEntity deleteById(PriceId id) {
        if (priceRepository.existsByMedicineIdAndPharmacyId(id.getMedicineId(), id.getPharmacyId())) {
            priceRepository.deleteByMedicineIdAndPharmacyId(id.getMedicineId(), id.getPharmacyId());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<PriceResponseDto> update(PriceId id, PriceRequestDto entity) {
        return this.priceRepository.findAllByMedicineIdAndPharmacyId(id.getMedicineId(), id.getPharmacyId())
                .map(p -> ResponseEntity.ok(entity.getMappedDtoEntity()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
