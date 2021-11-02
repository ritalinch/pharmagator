package com.eleks.academy.pharmagator.converters.medicine;

import com.eleks.academy.pharmagator.dto.MedicineDto;
import com.eleks.academy.pharmagator.requestEntities.PriceRequestDto;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class MedicineDto_toPriceRequestDto implements MedicineDto_toRequestDtoConverter<PriceRequestDto> {

    @Override
    public PriceRequestDto get(MedicineDto dto) {
        PriceRequestDto price = new PriceRequestDto();
        price.setPrice(dto.getPrice());
        price.setExternalId(dto.getExternalId());
        price.setUpdatedAt(Instant.now());
        return price;
    }

}
