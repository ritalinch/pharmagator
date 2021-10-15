package com.eleks.academy.pharmagator.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Getter
@Setter
@Table(name = "pharmacies")
public class Pharmacy {
    @Id
    private long id;
    private String name;
    private String medicineLinkTemplate;
}
