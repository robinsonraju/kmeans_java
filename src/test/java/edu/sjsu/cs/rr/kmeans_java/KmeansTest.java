package edu.sjsu.cs.rr.kmeans_java;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class KmeansTest extends TestCase {
	Kmeans kmeans = new Kmeans();
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public KmeansTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(KmeansTest.class);
	}

	/**
	 * Test valid input data
	 */
	public void testValidInputData() {
		String[] input = {"3", "5", ".1","euclidean","random","src/main/resources/iris-data.txt", "src/main/resources/output.txt"};
		
		try {
			kmeans.validateInputData(input);
			assertTrue(true);
		} catch (Exception e) {
			fail("Input validation failed");
		}
	}
	

	/**
	 * Test invalid input data
	 */
	public void testInValidInputData1() {
		String[] input = {"3", "5", ".1","euclidean","random","src/main/resources/iris-data.txt", "src/main/resources/output.txt"};
		
		input[0] = "sdf";
		try {
			kmeans.validateInputData(input);
			fail("Input validation failed");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	public void testInValidInputData2() {
		String[] input = {"3", "5", ".1","euclidean","random","src/main/resources/iris-data.txt", "src/main/resources/output.txt"};
		
		input[1] = "sdf";
		try {
			kmeans.validateInputData(input);
			fail("Input validation failed");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	public void testInValidInputData3() {
		String[] input = {"3", "5", ".1","euclidean","random","src/main/resources/iris-data.txt", "src/main/resources/output.txt"};
		
		input[2] = "sdf";
		try {
			kmeans.validateInputData(input);
			fail("Input validation failed");
		} catch (Exception e) {
			assertTrue(true);
		}
		
		input[2] = "0";
		try {
			kmeans.validateInputData(input);
			fail("Input validation failed");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	public void testInValidInputData4() {
		String[] input = {"3", "5", ".1","euclidean","random","src/main/resources/iris-data.txt", "src/main/resources/output.txt"};
		
		input[3] = "eucleedean";
		try {
			kmeans.validateInputData(input);
			fail("Input validation failed");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	public void testInValidInputData5() {
		String[] input = {"3", "5", ".1","euclidean","random","src/main/resources/iris-data.txt", "src/main/resources/output.txt"};
		
		input[4] = "randomds";
		try {
			kmeans.validateInputData(input);
			fail("Input validation failed");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	public void testInValidInputData6() {
		String[] input = {"3", "5", ".1","euclidean","random","src/main/resources/iris-data.txt", "src/main/resources/output.txt"};
		
		input[5] = "src/main/resources/iris-datasdf.txt";
		try {
			kmeans.validateInputData(input);
			fail("Input validation failed");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	public void testInValidInputData7() {
		String[] input = {"3", "5", ".1","euclidean","random","src/main/resources/iris-data.txt", "src/main/resources/output.txt"};
		
		input[6] = "";
		try {
			kmeans.validateInputData(input);
			fail("Input validation failed");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
}

