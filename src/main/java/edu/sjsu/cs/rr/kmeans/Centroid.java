package edu.sjsu.cs.rr.kmeans;

import java.util.ArrayList;
import java.util.Arrays;
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
	public double[][] getCentroids(double[][] dataSet, int k, InitMethod initMethod){
		double centroid[][] = new double[k][dataSet[0].length];
		switch (initMethod) {
			case partition:
				
				break;
			case random:
				int[] indexes = getRandomIndexes(dataSet, k);
				return assignCentroids(dataSet, centroid, indexes);
	
			default:
				break;
		}

        return centroid;
	}
	
	/**
	 * Return k random indexes
	 * @param dataSet
	 * @param k
	 * @return indexes int[]
	 */
	public int[] getRandomIndexes (double[][] dataSet, int k) {
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
	
	public int[] getPartitionIndexes (double[][] dataSet, int k) {
		int[] indexes = new int[k];
		List<Integer> list = new ArrayList<Integer>();
		
		for (int i = 0; i < dataSet.length; i++) {
			list.add(i);
		}
		Collections.shuffle(list);

		for (int i = 0; i < indexes.length; i++) {
			indexes[i] = list.get(i);
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
	private double[][] assignCentroids(double[][] dataSet, double centroid[][], int[] indexes){
		for (int i = 0; i < indexes.length; i++) {
			System.out.println("index " + indexes[i] + Arrays.toString(dataSet[indexes[i]]));
			centroid[i] = dataSet[indexes[i]];
		}
		return centroid;
	}

}
