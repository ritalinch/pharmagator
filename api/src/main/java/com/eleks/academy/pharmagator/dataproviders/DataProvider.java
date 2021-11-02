package com.eleks.academy.pharmagator.dataproviders;

import com.eleks.academy.pharmagator.dto.MedicineDto;

import java.util.stream.Stream;

public interface DataProvider {

    Stream<MedicineDto> loadData();

}
