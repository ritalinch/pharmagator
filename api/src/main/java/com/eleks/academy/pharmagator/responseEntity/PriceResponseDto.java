package com.eleks.academy.pharmagator.responseEntity;

import com.eleks.academy.pharmagator.entities.Price;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceResponseDto implements ResponseDto {

    private Long medicineId;

    private Long pharmacyId;

    private BigDecimal price;

    private Instant updatedAt;

    public PriceResponseDto(Price price) {
        this.medicineId = price.getMedicineId();
        this.pharmacyId = price.getPharmacyId();
        this.price = price.getPrice();
        this.updatedAt = price.getUpdatedAt();
    }

}
