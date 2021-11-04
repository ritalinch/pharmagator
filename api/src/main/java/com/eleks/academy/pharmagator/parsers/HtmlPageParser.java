package com.eleks.academy.pharmagator.parsers;

import com.eleks.academy.pharmagator.dto.MedicineDto;

import java.util.stream.Stream;

public interface HtmlPageParser {

    Stream<MedicineDto> getMedicinesFromPageByUrl(String url);

}
