package com.eleks.academy.pharmagator.dataproviders.socialna;

import com.eleks.academy.pharmagator.dataproviders.DataProvider;
import com.eleks.academy.pharmagator.dto.MedicineDto;
import com.eleks.academy.pharmagator.parsers.socialna.SocialnaParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Qualifier("pharmacySocialnaDataProvider")
public class PharmacySocialnaDataProvider implements DataProvider {

    @Value("${pharmagator.data-providers.socialna.url}")
    private String pharmacySocialnaBaseUrl;

    private SocialnaParser socialnaParser;

    @Autowired
    public void setSocialnaParser(SocialnaParser socialnaParser) {
        this.socialnaParser = socialnaParser;
    }

    @Override
    public Stream<MedicineDto> loadData() {
        return generateAllUrls().stream()
                .flatMap(url -> socialnaParser.getMedicinesFromPageByUrl(url));
    }

    private List<String> generateAllUrls() {
        return IntStream.rangeClosed(1, socialnaParser.getNumberOfPages())
                .mapToObj((int i) -> pharmacySocialnaBaseUrl + "?p=" + i)
                .collect(Collectors.toList());
    }

}
