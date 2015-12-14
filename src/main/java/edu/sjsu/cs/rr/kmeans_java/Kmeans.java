package edu.sjsu.cs.rr.kmeans_java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Class to cluster data using KMeans
 * 
 * @author rraju
 *
 */
public class Kmeans {
	// Input type
	private enum InputType {
		K("Value of K"), 
		MAX_ITER("Max Iterations"), 
		DELTA("Max Iterations"),
		DISTANCE("Distance Function"),
		INIT("Initialization Method"),
		FEATURE_COUNT("Feature Count"), 
		INPUT_FILE("Input file"),
		OUTPUT_FILE("Output file");

		private String inputTypeStr;

		private InputType(String c) {
			inputTypeStr = c;
		}

		public String getInputTypeStr() {
			return inputTypeStr;
		}
	}

	// Distance either “euclidean” or “cosine” distance,
	private enum Distance {
		euclidean, cosine
	};

	// Initialization method
	private enum InitMethod {
		random, partition
	};

	/**
	 * The program would have the following syntax java -cp kmeans.jar
	 * cs286.Kmeans <k> <max-iterations> <delta> <dist> <init> <input> <output>
	 * 
	 * TODO exception handling
	 * 
	 * @param args Input to the program
	 */
	public static void main(String[] args) throws Exception{
		Kmeans kmeans = new Kmeans();
		
		// Validate input data
		kmeans.validateInputData(args);


	}

	public void validateInputData (String[] args) throws Exception {
		System.out.println("**** Reading Input parameters ****");

		int k = getInput(args[0], InputType.K, new Integer(0));
		System.out.println("Number of clusters (Value of k) : " + k);

		int maxIter = getInput(args[1], InputType.MAX_ITER, new Integer(0));
		System.out.println("Max Iterations : " + maxIter);
		
		float delta = getInput(args[2], InputType.DELTA, new Float(0)).floatValue();
		System.out.println("Delta : " + delta);
		
		Distance dist = (Distance) getInput(args[3], InputType.DISTANCE, new Object());
		System.out.println("Distance : " + dist);
		
		InitMethod init = (InitMethod) getInput(args[4], InputType.INIT, new Object());
		System.out.println("Init : " + init);

		File input = (File) getInput(args[5], InputType.INPUT_FILE,new Object());
		System.out.println("Input file location : " + input.getAbsolutePath());

		File output = (File) getInput(args[6], InputType.OUTPUT_FILE,new Object());
		System.out.println("Output file location : " + output.getAbsolutePath());
		
		// int featureCount = getInput(args[7], InputType.FEATURE_COUNT, new Integer(0));
		int featureCount = 4; 
		System.out.println("Number of features : " + featureCount);
	}
	
	/**
	 * 
	 */
	private static void clusterData(
			final int k, 
			final int maxIter, 
			final float delta,
			final Distance dist, 
			final InitMethod init, 
			final File input, 
			final File output) {
		//TODO

	}

	@SuppressWarnings("unchecked")
	private static <T> T getInput(String input, InputType inputType,
			T outputType) throws Exception {
		
		
		try {
			switch (inputType) {
			case K:
				return (T) Integer.valueOf(input);
				
			case MAX_ITER:
				return (T) Integer.valueOf(input);
			
			case DELTA:
				return (T) Float.valueOf(input);
				
			case DISTANCE:
				return (T) Distance.valueOf(input);
				
			case INIT:
				return (T) InitMethod.valueOf(input);
				
			case INPUT_FILE:
				return (T) checkFile(input);
			
			case OUTPUT_FILE:
				if (notNullOrEmpty(input)) {
					return (T) new File(input);	
				} else {
					throw new Exception();
				}
				
			case FEATURE_COUNT:
				return (T) Integer.valueOf(input);

			default:
				throw new Exception("Invalid Input : "
						+ inputType.getInputTypeStr() + " " + input);		
			}
		} catch (Exception e) {
			throw new Exception("Invalid Input : "
					+ inputType.getInputTypeStr() + " " + input, e);
		}
	}
	
	/**
	 * 
	 * @param filePathString
	 * @return File
	 * @throws Exception
	 */
	private static File checkFile(String filePathString) throws Exception {
		File f = new File(filePathString);
		if (f.exists() && !f.isDirectory()) { 
		    return f;
		} else {
			throw new FileNotFoundException();
		}
	}
	
	/**
	 * 
	 * @param str
	 * @return boolean
	 */
	private static boolean notNullOrEmpty (String str) {
		return (str != null && !str.isEmpty());
	}
}
