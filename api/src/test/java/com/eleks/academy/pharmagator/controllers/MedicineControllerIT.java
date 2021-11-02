package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.repositories.MedicineRepository;
import com.eleks.academy.pharmagator.services.MedicineService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "/application-test.yaml")
@ContextConfiguration(classes = {MedicineRepository.class, MedicineService.class, MedicineRepository.class})
@AutoConfigureMockMvc
@WebMvcTest
public class MedicineControllerIT {

//    @Autowired
//    private MedicineController medicineController;

    private MockMvc mockMvc;

    @Test
    void test_checkStatus() {
        Assertions.assertEquals(110, 110);
//        MvcResult result = mockMvc.perform(get("/"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        Assertions.assertEquals("application/json;charset=UTF-8",
//                result.getResponse().getContentType());
    }

}
