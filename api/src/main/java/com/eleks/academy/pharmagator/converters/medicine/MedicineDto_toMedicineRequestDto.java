package com.eleks.academy.pharmagator.converters.medicine;

import com.eleks.academy.pharmagator.dto.MedicineDto;
import com.eleks.academy.pharmagator.requestEntities.MedicineRequestDto;
import org.springframework.stereotype.Component;

@Component
public class MedicineDto_toMedicineRequestDto implements MedicineDto_toRequestDtoConverter<MedicineRequestDto> {

    @Override
    public MedicineRequestDto get(MedicineDto dto) {
        return new MedicineRequestDto(dto.getTitle());
    }

}
