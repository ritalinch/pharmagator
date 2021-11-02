package com.eleks.academy.pharmagator.dto.ds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DSMedicinesResponse {

    private Long total;
    private List<DSMedicineDto> products;

}
