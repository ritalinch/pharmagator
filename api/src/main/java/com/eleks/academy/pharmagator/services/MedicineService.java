package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.dataproviders.dto.input.MedicineDto;
import com.eleks.academy.pharmagator.entities.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MedicineService {

    Page<Medicine> findAllPaginated(Pageable pageable);

    List<Medicine> findAll();

    Optional<Medicine> findById(Long id);

    Medicine save(MedicineDto medicineDto);

    Optional<Medicine> update(Long id, MedicineDto medicineDto);

    void deleteById(Long id);

<<<<<<< HEAD
<<<<<<< HEAD
=======
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
>>>>>>> 6efd804 (Fix "delete" methods in controllers and services)
}
=======
}
>>>>>>> 77f3be9 (Update)
