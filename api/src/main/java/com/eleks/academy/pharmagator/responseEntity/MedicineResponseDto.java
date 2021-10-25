package com.eleks.academy.pharmagator.responseEntity;

import com.eleks.academy.pharmagator.entities.Medicine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineResponseDto implements ResponseDto {

    private Long id;

    private String title;

    public MedicineResponseDto(Medicine medicine) {
        this.id = medicine.getId();
        this.title = medicine.getTitle();
    }
}
