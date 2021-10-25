package com.eleks.academy.pharmagator.requestEntities;

import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.responseEntity.MedicineResponseDto;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineRequestDto implements RequestDto<Medicine, MedicineResponseDto> {

    @NotEmpty
    private String title;

    public Medicine getMappedEntity() {
        return Medicine.builder()
                .id(null)
                .title(this.getTitle())
                .build();
    }

    public MedicineResponseDto getMappedDtoEntity() {
        return new MedicineResponseDto(Medicine.builder()
                .id(null)
                .title(this.getTitle())
                .build());
    }

}
