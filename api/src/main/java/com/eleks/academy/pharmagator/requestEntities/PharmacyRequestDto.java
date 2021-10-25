package com.eleks.academy.pharmagator.requestEntities;

import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.responseEntity.PharmacyResponseDto;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyRequestDto implements RequestDto<Pharmacy, PharmacyResponseDto> {

    @NotEmpty
    private String name;

    @NotEmpty
    private String medicineLinkTemplate;

    public Pharmacy getMappedEntity() {
        return Pharmacy.builder()
                .id(null)
                .name(this.getName())
                .medicineLinkTemplate(this.getMedicineLinkTemplate())
                .build();
    }

    public PharmacyResponseDto getMappedDtoEntity() {
        return new PharmacyResponseDto(Pharmacy.builder()
                .id(null)
                .name(this.getName())
                .medicineLinkTemplate(this.getMedicineLinkTemplate())
                .build());
    }

}