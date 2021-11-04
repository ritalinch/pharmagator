package com.eleks.academy.pharmagator.dto.socialna;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocialnaMedicineDto {

    private String name;
    private BigDecimal price;

}
