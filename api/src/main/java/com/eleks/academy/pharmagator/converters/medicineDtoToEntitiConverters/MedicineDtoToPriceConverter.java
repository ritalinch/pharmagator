package com.eleks.academy.pharmagator.converters.medicineDtoToEntitiConverters;

import com.eleks.academy.pharmagator.dataproviders.dto.MedicineDto;
import com.eleks.academy.pharmagator.entities.Price;
import org.modelmapper.ModelMapper;

public class MedicineDtoToPriceConverter implements MedicineDtoToEntityConverter<Price>{
    @Override
    public Price getEntity(MedicineDto medicineDto) {
        return new ModelMapper().map(medicineDto, Price.class);
    }
}
