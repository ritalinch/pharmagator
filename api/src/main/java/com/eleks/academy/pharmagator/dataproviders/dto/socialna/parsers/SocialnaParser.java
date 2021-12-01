package com.eleks.academy.pharmagator.dataproviders.dto.socialna.parsers;

import com.eleks.academy.pharmagator.dataproviders.dto.MedicineDto;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@Qualifier(value = "socialnaParser")
public class SocialnaParser {

    @Value("${pharmagator.data-providers.apteka-socialna.url}")
    private String pharmacySocialnaBaseUrl;

    @Value("${pharmagator.data-providers.apteka-socialna.pharmacy-name}")
    private String pharmacyName;

    public int getNumberOfPages() {
        try {
            Element amountElement = Jsoup.connect(pharmacySocialnaBaseUrl).get().select("div.pager > p.amount").first();
            String ans = amountElement.ownText().trim();
            Matcher matcher = Pattern.compile("[0-9]+$").matcher(ans);
            matcher.find();
            int total = Integer.parseInt(matcher.group(0));

            matcher = Pattern.compile("до\\s[0-9]+\\sз").matcher(ans);
            matcher.find();
            matcher = Pattern.compile("[0-9]+").matcher(matcher.group(0));
            matcher.find();
            short per = Short.parseShort(matcher.group(0));

            return (total % per == 0)
                    ? total / per
                    : total / per + 1;

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return 0;
    }

    public Stream<MedicineDto> getMedicinesFromPageByUrl(String url) {
        try {
            return Jsoup.connect(url).get().select(".product-item-details").stream()
                    .filter(e -> !e.children().eachText().contains("Відсутній на складі"))
                    .map(this::mapElementToMedicineDto);

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return Stream.of();
    }

    private MedicineDto mapElementToMedicineDto(Element element) {
        return MedicineDto.builder()
                .title(element.select("span.gproductname").first().ownText())
                .price(new BigDecimal(element.select("span.gproductprice").first().ownText()))
                .externalId(element.select("span.gproductid").first().ownText())
                .pharmacyName(pharmacyName)
                .build();
    }

}