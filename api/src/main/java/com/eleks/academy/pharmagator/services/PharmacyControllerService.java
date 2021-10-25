package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import com.eleks.academy.pharmagator.requestEntities.PharmacyRequestDto;
import com.eleks.academy.pharmagator.responseEntity.PharmacyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PharmacyControllerService implements ControllerService<Long, PharmacyRequestDto, PharmacyResponseDto> {

    private final PharmacyRepository pharmacyRepository;

    @Override
    public List<PharmacyResponseDto> getAll() {
        return pharmacyRepository
                .findAll()
                .stream()
                .map(PharmacyResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<PharmacyResponseDto> getById(Long id) {
        return pharmacyRepository.findById(id)
                .map(pharmacy -> ResponseEntity.ok(new PharmacyResponseDto(pharmacy)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public void create(PharmacyRequestDto pharmacyRequestDto) {
        pharmacyRepository.save(pharmacyRequestDto.getMappedEntity());
    }

    @Override
    public ResponseEntity deleteById(Long id) {
        if (pharmacyRepository.existsById(id)) {
            pharmacyRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<PharmacyResponseDto> update(Long id, PharmacyRequestDto entity) {
        return this.pharmacyRepository.findById(id)
                .map(p -> ResponseEntity.ok(entity.getMappedDtoEntity()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
