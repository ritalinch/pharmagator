package com.eleks.academy.pharmagator.dto.ds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class DSMedicineDto {

    private String id;

    private String name;

    private BigDecimal price;

    private String manufacturer;

}
