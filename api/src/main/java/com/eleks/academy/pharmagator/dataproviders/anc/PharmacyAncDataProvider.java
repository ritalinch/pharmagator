package com.eleks.academy.pharmagator.dataproviders.anc;

import com.eleks.academy.pharmagator.dataproviders.DataProvider;
import com.eleks.academy.pharmagator.dto.MedicineDto;
import com.eleks.academy.pharmagator.dto.anc.AncCategoriesResponse;
import com.eleks.academy.pharmagator.dto.anc.AncCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Qualifier("pharmacyAncDataProvider")
public class PharmacyAncDataProvider implements DataProvider {

    private WebClient pharmacyAncWebClient;
    @Value("${pharmagator.data-providers.anc.category-path}")
    private String categoryPathUrl;

    @Autowired
    public void setPharmacyAncWebClient(WebClient pharmacyAncWebClient) {
        this.pharmacyAncWebClient = pharmacyAncWebClient;
    }

    @Override
    public Stream<MedicineDto> loadData() {
        return this.getAllCategories().stream()
                .filter(categoryDto -> categoryDto.getName().equals("Медикаменти"))
                .map(AncCategoryDto::getSubcategories)
                .flatMap(Collection::stream)
                .map(AncCategoryDto::getSubcategories)
                .flatMap(Collection::stream)
                .map(AncCategoryDto::getLink)
                .flatMap(this::fetchMedicinesByCategory);
    }

    private Stream<MedicineDto> fetchMedicinesByCategory(String category) {

        AncMedicinesResponse ancMedicinesResponse = this.pharmacyAncWebClient.get()
                .uri(categoryPathUrl + "/" + category)
                .retrieve()
                .bodyToMono(AncMedicinesResponse.class)
                .block();

        return ancMedicinesResponse == null ? Stream.of() : ancMedicinesResponse.getProducts().stream()
                .map(this::mapToMedicineDto);

    }

    private List<AncCategoryDto> getAllCategories() {
        final var categories = this.pharmacyAncWebClient.get().uri(categoryPathUrl)
                .retrieve()
                .bodyToMono(AncCategoriesResponse.class)
                .block();
        return categories == null ? Collections.emptyList() : categories.getCategories();
    }

    private MedicineDto mapToMedicineDto(AncMedicineDto ancMedicineDto) {
        return MedicineDto.builder()
                .externalId(ancMedicineDto.getId())
                .price(ancMedicineDto.getPrice())
                .title(ancMedicineDto.getName())
                .build();
    }
}
