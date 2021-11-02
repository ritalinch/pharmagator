package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.repositories.MedicineRepository;
import com.eleks.academy.pharmagator.requestEntities.MedicineRequestDto;
import com.eleks.academy.pharmagator.responseEntity.MedicineResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicineService implements ControllerService<Long, MedicineRequestDto, MedicineResponseDto> {

    private final MedicineRepository medicineRepository;

    public int getAllByTitle(@Valid String title) {
        return medicineRepository.countAllByTitle(title);
    }

    public boolean existsByTitle(@Valid String title) {
        return medicineRepository.existsByTitle(title);
    }

    @Override
    public List<MedicineResponseDto> getAll() {
        return medicineRepository
                .findAll()
                .stream()
                .map(MedicineResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<MedicineResponseDto> getById(Long id) {
        return medicineRepository.findById(id)
                .map(medicine -> ResponseEntity.ok(new MedicineResponseDto(medicine)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public void create(MedicineRequestDto medicineRequestDto) {
        if (!medicineRepository.existsByTitle(medicineRequestDto.getTitle())) {
            medicineRepository.save(medicineRequestDto.getMappedEntity());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (medicineRepository.existsById(id)) {
            medicineRepository.deleteById(id);
        }
    }

    @Override
    public ResponseEntity<MedicineResponseDto> update(Long id, MedicineRequestDto entity) {
        return this.medicineRepository.findById(id)
                .map(m -> ResponseEntity.ok(entity.getMappedDtoEntity()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
