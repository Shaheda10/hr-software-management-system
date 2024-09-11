package com.hr.management;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class ComHrManagementApplicationTests {

	Calculator underTest = new Calculator();

	@Test
	void itShouldAddNumbers() {
		int numberOne = 10;
		int numberTwo = 20;

		int expected = 30;

		int result = underTest.add(numberOne, numberTwo);
		assertThat(result).isEqualTo(expected);

	}

	class Calculator {
		int add(int a, int b) {
			return a + b;

		}
	}

}
