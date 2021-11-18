package com.eleks.academy.pharmagator.dataproviders;

import java.util.stream.Stream;

public interface DataProvider {

    Stream<MedicineDto> loadData();

}
