package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.dataproviders.dto.input.PharmacyDto;
import com.eleks.academy.pharmagator.entities.Pharmacy;

import java.util.List;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 77f3be9 (Update)
import java.util.Optional;

public interface PharmacyService {

    List<Pharmacy> findAll();

    Optional<Pharmacy> findById(Long id);

    Pharmacy save(PharmacyDto pharmacyDto);

    Optional<Pharmacy> update(Long id, PharmacyDto pharmacyDto);

    void deleteById(Long id);
<<<<<<< HEAD
=======
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PharmacyService implements ControllerService<Long, PharmacyRequestDto, PharmacyResponseDto> {

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
    public void deleteById(Long id) {
        if (pharmacyRepository.existsById(id)) {
            pharmacyRepository.deleteById(id);
        }
    }

    @Override
    public ResponseEntity<PharmacyResponseDto> update(Long id, PharmacyRequestDto entity) {
        return this.pharmacyRepository.findById(id)
                .map(p -> ResponseEntity.ok(entity.getMappedDtoEntity()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
>>>>>>> 6efd804 (Fix "delete" methods in controllers and services)

}
=======

}
>>>>>>> 77f3be9 (Update)
