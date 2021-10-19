package com.eleks.academy.pharmagator.dataproviders.dummy;

import com.eleks.academy.pharmagator.dataproviders.PharmacyDataProvider;
import com.eleks.academy.pharmagator.dataproviders.dto.MedicineDto;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class DummyProviderImpl implements PharmacyDataProvider {
    private PharmacyRepository pharmacyRepository;
    @Override
    public Stream<MedicineDto> loadData() {
        return IntStream.rangeClosed(1, 100)
                .mapToObj(this::buildDto);
    }

    private MedicineDto buildDto(int i) {
        return MedicineDto.builder()
                .externalId(String.valueOf(i))
                .title("title" + i)
                .price(BigDecimal.valueOf(Math.random()))
                .build();
    }

    @Override
    public Pharmacy getPharmacy() {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setName("Dummy");
        pharmacy.setMedicineLinkTemplate("dummy");
        Optional<Pharmacy> optionalPharmacy = pharmacyRepository.findByName("Dummy");
        if(optionalPharmacy.isEmpty()){
            pharmacyRepository.saveAndFlush(pharmacy);
        }else {
            pharmacy = optionalPharmacy.get();
        }
        return pharmacy;
    }
}
