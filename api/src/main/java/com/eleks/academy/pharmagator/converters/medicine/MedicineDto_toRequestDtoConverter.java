package com.eleks.academy.pharmagator.converters.medicine;

import com.eleks.academy.pharmagator.dto.MedicineDto;
import com.eleks.academy.pharmagator.requestEntities.RequestDto;

public interface MedicineDto_toRequestDtoConverter<T extends RequestDto> {
    T get(MedicineDto dto);
}
