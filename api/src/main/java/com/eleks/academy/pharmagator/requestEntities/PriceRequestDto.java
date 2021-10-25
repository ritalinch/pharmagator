package com.eleks.academy.pharmagator.requestEntities;

import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.responseEntity.PriceResponseDto;
import lombok.*;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceRequestDto implements RequestDto<Price, PriceResponseDto> {
    
    @NotEmpty
    private Long medicineId;
    
    @NotEmpty
    private Long pharmacyId;

    @NotEmpty
    private BigDecimal price;

    @NotEmpty
    private String externalId;

    @NotEmpty
    private Instant updatedAt;
    
    public Price getMappedEntity() {
        return Price.builder()
                .medicineId(this.getMedicineId())
                .pharmacyId(this.getPharmacyId())
                .externalId(this.getExternalId())
                .price(this.getPrice())
                .updatedAt(this.getUpdatedAt())
                .build();
    }

    public PriceResponseDto getMappedDtoEntity() {
        return new PriceResponseDto(Price.builder()
                .medicineId(this.getMedicineId())
                .pharmacyId(this.getPharmacyId())
                .externalId(this.getExternalId())
                .price(this.getPrice())
                .updatedAt(this.getUpdatedAt())
                .build());
    }

    public ResponseEntity update(Price price) {
        price.setPrice(this.getPrice());
        price.setExternalId(this.getExternalId());
        price.setUpdatedAt(this.getUpdatedAt());
        return ResponseEntity.ok().build();
    }

}
