package com.eleks.academy.pharmagator.responseEntity;

import com.eleks.academy.pharmagator.entities.Pharmacy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyResponseDto implements ResponseDto<Pharmacy> {

    private Long id;

    private String name;

    public PharmacyResponseDto(Pharmacy pharmacy) {
        this.id = pharmacy.getId();
        this.name = pharmacy.getName();
    }
}
