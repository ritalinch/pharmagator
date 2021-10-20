package com.eleks.academy.pharmagator.entities.requestEntities;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String medicineLinkTemplate;

}