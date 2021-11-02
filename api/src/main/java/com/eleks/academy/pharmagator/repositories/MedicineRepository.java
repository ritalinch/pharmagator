package com.eleks.academy.pharmagator.repositories;

import com.eleks.academy.pharmagator.entities.Medicine;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    boolean existsByTitle(@NotEmpty String title);

    int countAllByTitle(@NotEmpty String title);

}
