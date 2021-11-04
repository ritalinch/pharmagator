package com.eleks.academy.pharmagator.dto.socialna;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {

    private int numberOfPages;
    private short medicinePerPage;

}
