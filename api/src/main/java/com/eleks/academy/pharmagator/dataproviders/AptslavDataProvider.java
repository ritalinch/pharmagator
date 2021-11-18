package com.eleks.academy.pharmagator.dataproviders;

import com.eleks.academy.pharmagator.dataproviders.dto.aptslav.AptslavMedicineDto;
import com.eleks.academy.pharmagator.dataproviders.dto.aptslav.AptslavResponseBody;
import com.eleks.academy.pharmagator.dataproviders.dto.aptslav.converters.ApiDtoConverter;
import com.eleks.academy.pharmagator.dataproviders.dto.input.MedicineDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
@Qualifier("aptslavDataProvider")
public class AptslavDataProvider implements DataProvider {

    @Value("${pharmagator.data-providers.aptslav.categories-url}")
    private String categoriesFetchUrl;

    @Value("${pharmagator.data-providers.aptslav.medicines-uri}")
    private String medicinesFetchUri;

    @Value("${pharmagator.data-providers.aptslav.medicineLinkTemplate}")
    private String medicineLinkTemplate;

    @Value("${pharmagator.data-providers.aptslav.title}")
    private String pharmacyTitle;

    @Value("${pharmagator.data-providers.aptslav.page-size}")
    private Integer pageSize;

    @Qualifier("aptslavWebClient")
    private final WebClient aptslavWebClient;

    private final ApiDtoConverter<AptslavMedicineDto> apiDtoConverter;

    @Override
    public Stream<MedicineDto> loadData() {

        return fetchMedicines();

    }


    /**
     * Here in this method we`re sending GET requests to the API until we`ve read all available data
     * While API`s 'take' parameter can`t be more than 100,we can`t fetch all available data in one
     * request
     * For now we`re taking only those products,which are in medicines category
     *
     * @return Stream<MedicineDto>
     */
    protected Stream<MedicineDto> fetchMedicines() {

        AptslavResponseBody<AptslavMedicineDto> initialResponse = sendGetMedicinesRequest(pageSize, 0);

        if (initialResponse != null) {

            long dataSetCount = initialResponse.getCount();

            long steps = calculateTotalPages(dataSetCount);

            Stream<AptslavMedicineDto> restOfData = LongStream.rangeClosed(1, steps)
                    .boxed()
                    .map(s -> sendGetMedicinesRequest(pageSize, (int) (s * pageSize)))
                    .map(AptslavResponseBody::getData)
                    .flatMap(Collection::stream);

            return Stream.concat(initialResponse.getData().stream(), restOfData)
                    .map(apiDtoConverter::toMedicineDto);

        } else {

            return Stream.empty();

        }

    }

    protected long calculateTotalPages(long dataSetCount) {

        long totalPages = dataSetCount / pageSize;

        return dataSetCount % pageSize == 0 ? totalPages : totalPages + 1;

    }

    /**
     * @param step - how many objects we can retrieve,represents API`s 'take' parameter.
     *             According to API, max value is 100,default value is 5
     * @param skip - how many objects we already have,represents API`s 'skip' parameter
     * @return AptslavResponseBody<AptslavMedicineDto>
     * @see AptslavResponseBody
     */
    protected AptslavResponseBody<AptslavMedicineDto> sendGetMedicinesRequest(int step, int skip) {

        if(step <= 0 || step > 100){

            throw new IllegalArgumentException("'step' param can't be less than 0 and greater than 100");

        }

        return aptslavWebClient
                .get()
                .uri(uriBuilder -> uriBuilder.path(medicinesFetchUri)
                        .queryParam("fields", "id,externalId,name,created,manufacturer")
                        .queryParam("take", step)
                        .queryParam("skip", skip)
                        .queryParam("inStock", true)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<AptslavResponseBody<AptslavMedicineDto>>() {
                })
                .block();
    }

}