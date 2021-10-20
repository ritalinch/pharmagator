package com.eleks.academy.pharmagator.entities.requestEntities;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineRequest {

    @NotEmpty
    private String title;

}
