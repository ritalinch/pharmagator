package com.eleks.academy.pharmagator.dataproviders.dto.aptslav.converters;

public interface ApiDtoConverter<T> {
    MedicineDto toMedicineDto(T apiDto);
}
