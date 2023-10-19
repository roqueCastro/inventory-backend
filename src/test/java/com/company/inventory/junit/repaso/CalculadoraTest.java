package com.company.inventory.junit.repaso;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
	
	Calculadora cal;
	
	@BeforeAll
	public static void primero() {
		System.out.println("Primer");
	}
	
	@BeforeEach
	public void primeroPorCadaPrueba() {
		System.out.println("Primero por cada prueba");
		cal = new Calculadora();
	}
	
	
	@AfterEach
	public void ultimoPorCadaPrueba() {
		System.out.println("Ultimo por cada prueba");

	}
	
	@AfterAll
	public static void ultimo() {
		System.out.println("Ultimo");
	}

	
	@Test
	@DisplayName("Prueba de sumar calculadora")
	public void sumarTest() {
		assertEquals(2, cal.sumar(1, 1));
		assertNotEquals(2, cal.sumar(2, 1));
		assertFalse((2 == cal.sumar(2, 1)));
	}
	
	
	@Test
	@Disabled("No iniciar la resta test")
	public void restarTest() {
		assertEquals(4, cal.restar(5, 1));
//		assertNotEquals(2, cal.sumar(2, 1));
//		assertFalse((2 == cal.sumar(2, 1)));
	}
	
	@Test
	public void multiplicarTest() {
		assertEquals(25, cal.multiplicar(5, 5));
//		assertNotEquals(2, cal.sumar(2, 1));
//		assertFalse((2 == cal.sumar(2, 1)));
	}
	
	@Test
	public void dividirTest() {
		assertTrue((cal.dividir(10, 2) == 5));
//		assertNotEquals(2, cal.sumar(2, 1));
//		assertFalse((2 == cal.sumar(2, 1)));
	}

}
