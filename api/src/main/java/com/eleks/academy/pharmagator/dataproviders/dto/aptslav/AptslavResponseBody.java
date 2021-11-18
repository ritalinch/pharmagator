package com.eleks.academy.pharmagator.dataproviders.dto.aptslav;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AptslavResponseBody<T> {

    //  Each API response body consist of a list of data and size of that list
    //  T type stands for DTO type we want to retrieve

    private List<T> data;

    private long count;

}