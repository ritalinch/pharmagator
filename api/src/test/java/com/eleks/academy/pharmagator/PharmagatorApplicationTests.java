package com.eleks.academy.pharmagator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "/application-test.yaml")
class PharmagatorApplicationTests {

	@Test
	void contextLoads() {
	}

}
