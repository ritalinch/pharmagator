package com.eleks.academy.pharmagator.scheduler;

import com.eleks.academy.pharmagator.converters.medicine.MedicineDto_toMedicineRequestDto;
import com.eleks.academy.pharmagator.converters.medicine.MedicineDto_toPriceRequestDto;
import com.eleks.academy.pharmagator.dataproviders.DataProvider;
import com.eleks.academy.pharmagator.dto.MedicineDto;
import com.eleks.academy.pharmagator.services.MedicineService;
import com.eleks.academy.pharmagator.services.PriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final List<DataProvider> dataProvidersList;
    private final MedicineDto_toMedicineRequestDto medToMed;
    private final MedicineDto_toPriceRequestDto medToPrice;
    private final MedicineService medServe;
    private final PriceService priceServe;

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.MINUTES)
    public void schedule() {

        log.info("Scheduler started at {}", Instant.now());
        dataProvidersList.stream()
                .flatMap(DataProvider::loadData)
                .forEach(this::storeToDatabase);

    }

    private void storeToDatabase(MedicineDto dto) {
        log.info(dto.getTitle() + " " + dto.getPrice());
        medServe.create(medToMed.get(dto));
//        priceServe.create(medToPrice.get(dto));
    }
}