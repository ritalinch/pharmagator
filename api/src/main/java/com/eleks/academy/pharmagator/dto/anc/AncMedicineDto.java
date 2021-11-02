package com.eleks.academy.pharmagator.dto.anc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AncMedicineDto {

    private String id;
    private String name;
    private BigDecimal price;

}
