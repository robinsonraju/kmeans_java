package edu.sjsu.cs.rr.kmeans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Centroid {

	// Initialization method
	public enum InitMethod {
		random, partition
	};

	/**
	 * Returns k centroids
	 * 
	 * @param dataSet
	 * @param k
	 * @param initMethod
	 * @return centroids double[][]
	 */
	public static double[][] getCentroids(double[][] dataSet, int k,
			InitMethod initMethod) {
		double centroid[][] = new double[k][dataSet[0].length];
		int[] indexes = new int[k];
		switch (initMethod) {
			case partition:
				indexes = getPartitionIndexes(dataSet, k);
				return assignCentroids(dataSet, centroid, indexes);
			case random:
				indexes = getRandomIndexes(dataSet, k);
				return assignCentroids(dataSet, centroid, indexes);
	
			default:
				break;
		}

		return centroid;
	}

	/**
	 * Return k random indexes
	 * 
	 * @param dataSet
	 * @param k
	 * @return indexes int[]
	 */
	public static int[] getRandomIndexes(double[][] dataSet, int k) {
		int[] indexes = new int[k];
		List<Integer> list = new ArrayList<Integer>();

		// Add indexes to a list
		for (int i = 0; i < dataSet.length; i++) {
			list.add(i);
		}

		// Randomize
		Collections.shuffle(list);

		for (int i = 0; i < indexes.length; i++) {
			indexes[i] = list.get(i);
		}
		return indexes;
	}

	public static int[] getPartitionIndexes(double[][] dataSet, int k) {
		int[] indexes = new int[k];

		int rows = dataSet.length;
		int partitionLength = rows / k;
		int start = 0;
		int end = partitionLength;
		for (int i = 0; i < indexes.length; i++) {
			indexes[i] = (start + end) / 2;
			start = end;
			end += partitionLength;
		}
		return indexes;
	}

	/**
	 * 
	 * @param dataSet
	 * @param centroid
	 * @param indexes
	 * @return centroids double[][]
	 */
	private static double[][] assignCentroids(double[][] dataSet,
			double centroid[][], int[] indexes) {
		for (int i = 0; i < indexes.length; i++) {
			centroid[i] = dataSet[indexes[i]];
		}
		return centroid;
	}

}
