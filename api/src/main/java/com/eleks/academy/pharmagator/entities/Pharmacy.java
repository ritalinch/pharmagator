package com.eleks.academy.pharmagator.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pharmacies")
public class Pharmacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String medicineLinkTemplate;

}
