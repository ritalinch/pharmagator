package com.eleks.academy.pharmagator.dataproviders.ds;

import com.eleks.academy.pharmagator.dataproviders.DataProvider;
import com.eleks.academy.pharmagator.dto.MedicineDto;
import com.eleks.academy.pharmagator.dto.ds.DSCategoryDto;
import com.eleks.academy.pharmagator.dto.ds.DSMedicineDto;
import com.eleks.academy.pharmagator.dto.ds.DSMedicinesResponse;
import com.eleks.academy.pharmagator.dto.general.FilterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Qualifier("pharmacyDSDataProvider")
public class PharmacyDSDataProvider implements DataProvider {

    private final WebClient pharmacyDSWebClient;

    @Value("${pharmagator.data-providers.apteka-ds.category-fetch-url}")
    private String categoriesFetchUrl;

    @Value("${pharmagator.data-providers.apteka-ds.category-path}")
    private String categoryPath;

    @Override
    public Stream<MedicineDto> loadData() {
        return this.fetchCategories().stream()
                .filter(categoryDto -> categoryDto.getName().equals("Медикаменти"))
                .map(DSCategoryDto::getChildren)
                .flatMap(Collection::stream)
                .map(DSCategoryDto::getSlug)
                .flatMap(this::fetchMedicinesByCategory);
    }

    private List<DSCategoryDto> fetchCategories() {
        return this.pharmacyDSWebClient.get().uri(categoriesFetchUrl)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<DSCategoryDto>>() {
                }).block();
    }

    private Stream<MedicineDto> fetchMedicinesByCategory(String category) {

        Long pageSize = 100L;

        FilterRequest filterRequest = FilterRequest.builder()
                .page(1L)
                .per(100L)
                .build();

        DSMedicinesResponse dsMedicinesResponse = this.pharmacyDSWebClient.post()
                .uri(categoryPath + "/" + category)
                .body(Mono.just(filterRequest), FilterRequest.class)
                .retrieve()
                .bodyToMono(DSMedicinesResponse.class)
                .block();


        Long total;
        if (dsMedicinesResponse != null) {
            total = dsMedicinesResponse.getTotal();
            long pageCount = total / pageSize;

            List<DSMedicinesResponse> responseList = new ArrayList<>();
            long page = 1L;
            while (page <= pageCount) {
                DSMedicinesResponse singleMedicinesResponse = this.pharmacyDSWebClient.post()
                        .uri(categoryPath + "/" + category)
                        .body(Mono.just(FilterRequest.builder()
                                .page(page)
                                .per(pageSize)
                                .build()), FilterRequest.class)
                        .retrieve()
                        .bodyToMono(DSMedicinesResponse.class)
                        .block();
                responseList.add(singleMedicinesResponse);
                page++;
            }

            return responseList.stream()
                    .map(DSMedicinesResponse::getProducts)
                    .flatMap(Collection::stream)
                    .map(this::mapToMedicineDto);
        }
        return Stream.of();
    }

    private MedicineDto mapToMedicineDto(DSMedicineDto dsMedicineDto) {
        return MedicineDto.builder()
                .externalId(dsMedicineDto.getId())
                .price(dsMedicineDto.getPrice())
                .title(dsMedicineDto.getName())
                .build();
    }
}
