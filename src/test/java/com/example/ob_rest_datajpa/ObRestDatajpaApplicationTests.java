package com.example.ob_rest_datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObRestDatajpaApplicationTests {

	@Test
	void contextLoads() {
		System.getenv().forEach(
				(key, value)-> System.out.println(key+" "+value)
		);
	}

}
