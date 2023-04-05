package com.company.inventory.junit.repaso;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class AssertArrayTest {

	@Test
	public void assertArrayTest() {
		
		String [] array1 = {"aa", "bb"};
		String [] array2 = {"aa", "bb"};
		String [] array3 = {"aa", "bb", "cc"};
		
		assertArrayEquals(array1, array2);
		assertArrayEquals(array2, array1);
		
	}
}
