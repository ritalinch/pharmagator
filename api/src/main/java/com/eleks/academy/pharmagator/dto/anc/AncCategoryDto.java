package com.eleks.academy.pharmagator.dto.anc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AncCategoryDto {

    private Long id;
    private String name;
    private String link;
    private List<AncCategoryDto> subcategories;

}
