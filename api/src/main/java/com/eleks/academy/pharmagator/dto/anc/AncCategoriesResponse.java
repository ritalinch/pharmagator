package com.eleks.academy.pharmagator.dto.anc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AncCategoriesResponse {

    List<AncCategoryDto> categories;

}
