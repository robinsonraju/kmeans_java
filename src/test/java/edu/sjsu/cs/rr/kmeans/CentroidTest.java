package edu.sjsu.cs.rr.kmeans;

import java.util.Arrays;

import edu.sjsu.cs.rr.kmeans.Centroid;
import junit.framework.TestCase;

public class CentroidTest extends TestCase {

	public void testGetRandomIndexes() {
		double[][] dataSet = {
				{5.1,3.5,1.4,0.2},
				{4.9,3,1.4,0.2},
				{4.7,3.2,1.3,0.2},
				{4.6,3.1,1.5,0.2},
				{5,3.6,1.4,0.2},
				{5.4,3.9,1.7,0.4},
				{4.6,3.4,1.4,0.3},
				{5,3.4,1.5,0.2},
				{4.4,2.9,1.4,0.2},
				{4.9,3.1,1.5,0.1}
		};
		int[] origIndexes = {0, 1, 3};
		int[] randIndexes = Centroid.getRandomIndexes(dataSet, 3);
		
		// Truly random should always be false
		assertFalse(Arrays.equals(origIndexes, randIndexes));
	}
	
	public void testGetPartitionIndexes() {
		double[][] dataSet = {
				{5.1,3.5,1.4,0.2},
				{4.9,3,1.4,0.2},
				{4.7,3.2,1.3,0.2},
				{4.6,3.1,1.5,0.2},
				{5,3.6,1.4,0.2},
				{5.4,3.9,1.7,0.4},
				{4.6,3.4,1.4,0.3},
				{5,3.4,1.5,0.2},
				{4.4,2.9,1.4,0.2},
				{4.9,3.1,1.5,0.1},
				{4.9,3.1,1.5,0.1},
				{4.9,3.1,1.5,0.1}
		};
		int[] origIndexes = {2, 6, 10};
		int[] partIndexes = Centroid.getPartitionIndexes(dataSet, 3);
		
		// Should always be true
		assertTrue(Arrays.equals(origIndexes, partIndexes));
	}
	
}
