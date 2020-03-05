package fr.epita.maths.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epita.factorial.CalculateFactorial;


public class TestFactorial {
	
	private static final Logger LOGGER = LogManager.getLogger(TestFactorial.class);
	
	@BeforeClass
	public static void BeforeAll() {
		LOGGER.info("Before All");
	}
	
	@Before
	public void beforeEachTest() {
		System.out.println("before");
	}
	
	@Test
	public void PositiveIntTest() throws Exception {
		int posInt= 5;
		CalculateFactorial cf = new CalculateFactorial();
		int result = cf.CalcFact(posInt);
		System.out.println(result);
		if(result != 120) {
			throw new Exception("Bad Value for 5: it should return 120");
		}
	}
	
	@Test
	public void NegativeIntTest() throws Exception {
		int negInt= -5;
		CalculateFactorial cf = new CalculateFactorial();
			int result = cf.CalcFact(negInt);
			if(result == 120) {
				throw new Exception("Bad Value for 5: it should return 120");
			}
		
	}
	
	@After
	public void AfterEach() {
		System.out.println("After");
	}
	
	@AfterClass
	public static void AfterAll() {
		System.out.println("After All");
	}

}
