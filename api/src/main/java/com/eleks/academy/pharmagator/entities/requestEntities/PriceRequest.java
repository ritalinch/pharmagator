package com.eleks.academy.pharmagator.entities.requestEntities;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceRequest {

    @NotEmpty
    private BigDecimal price;

    @NotEmpty
    private String externalId;

    @NotEmpty
    private Instant updatedAt;

}
