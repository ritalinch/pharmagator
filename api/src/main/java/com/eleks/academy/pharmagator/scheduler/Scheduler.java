package com.eleks.academy.pharmagator.scheduler;

import com.eleks.academy.pharmagator.controllers.MedicineController;
import com.eleks.academy.pharmagator.controllers.PriceController;
import com.eleks.academy.pharmagator.converters.medicine.MedicineDtoToMedicineConverter;
import com.eleks.academy.pharmagator.converters.medicine.MedicineDtoToPriceConverter;
import com.eleks.academy.pharmagator.dataproviders.DataProvider;
import com.eleks.academy.pharmagator.dataproviders.dto.MedicineDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Component
public class Scheduler {
    private final DataProvider dataProvider;
    private MedicineController medicineController;
    private PriceController priceController;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void schedule() {
        log.info("Scheduler started at {}", Instant.now());
        dataProvider.loadData().forEach(this::storeToDatabase);
    }

    private void storeToDatabase(MedicineDto dto) {
        priceController.create(new MedicineDtoToPriceConverter().getEntity(dto));
        medicineController.create(new MedicineDtoToMedicineConverter().getEntity(dto));
    }
}
