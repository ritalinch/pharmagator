package com.eleks.academy.pharmagator.entities;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PriceId implements Serializable {

    private Long pharmacyId;

    private Long medicineId;

}
