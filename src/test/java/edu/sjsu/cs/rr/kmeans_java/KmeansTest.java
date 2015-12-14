package edu.sjsu.cs.rr.kmeans_java;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class KmeansTest extends TestCase {
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
	 * Rigourous Test :-)
	 */
	public void testKMeans() {
		String[] input = {"3", "5", ".1","euclidean","random","src/main/resources/iris-data.txt", "src/main/resources/output.txt"};
		Kmeans kmeans = new Kmeans();
		
		try {
			kmeans.validateInputData(input);
			assertTrue(true);
		} catch (Exception e) {
			fail("Input validation failed");
		}
		

	}
}
