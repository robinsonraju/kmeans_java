package edu.sjsu.cs.rr.kmeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import edu.sjsu.cs.rr.kmeans.Centroid.InitMethod;

/**
 * Class to cluster data using KMeans
 * 
 * @author rraju
 *
 */
public class Kmeans {
	// Input type
	public enum InputType {
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
	public enum Distance {
		euclidean, cosine
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
		KmeansInput input = kmeans.validateInputData(args);
		
		// Cluster data 
		kmeans.clusterData(input);


	}

	public KmeansInput validateInputData (String[] args) throws Exception {
		System.out.println("**** Reading Input parameters ****");

		int k = getInput(args[0], InputType.K, new Integer(0));

		int maxIter = getInput(args[1], InputType.MAX_ITER, new Integer(0));
		
		float delta = getInput(args[2], InputType.DELTA, new Float(0)).floatValue();
		
		Distance dist = (Distance) getInput(args[3], InputType.DISTANCE, new Object());
		
		InitMethod init = (InitMethod) getInput(args[4], InputType.INIT, new Object());

		File inputFile = (File) getInput(args[5], InputType.INPUT_FILE,new Object());
		
		File outputFile = (File) getInput(args[6], InputType.OUTPUT_FILE,new Object());
		
		// int featureCount = getInput(args[7], InputType.FEATURE_COUNT, new Integer(0));
		int featureCount = 4; 
		
		System.out.println("Input is valid.");
		return new KmeansInput(k, maxIter, delta, dist, init, inputFile, outputFile, featureCount);
		
	}
	
	/**
	 * 
	 */
	public void clusterData (KmeansInput input) throws Exception {
		System.out.println("Input to Kmeans - " + input.toString());
		
		int dataCount = 0;
		int featureCount = input.getFeatureCount();
		int k = input.getK();
		
		// Read Input data 
		List<String> inputData = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(input.getInputFile()))) {
			String line;
			while ((line = br.readLine()) != null) {
				inputData.add(line);
			}
		}
		dataCount = inputData.size();
		
		// Store data into a dataset matrix
		double dataSet[][] = new double[dataCount][featureCount];
        
        for(int i = 0; i < dataCount; ++i) {
        	StringTokenizer st = new StringTokenizer(inputData.get(i));
            for(int j = 0; j < featureCount; ++j) {
            	String token = st.nextToken();
                dataSet[i][j] = Double.parseDouble(token);
            }
        }

        // Get centroids
        Centroid chelper = new Centroid();
        double centroid[][] = chelper.getCentroids(dataSet, k, InitMethod.random);
        for (int i = 0; i < centroid.length; i++) {
			System.out.println(Arrays.toString(centroid[i]));
		}
        
        
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
				if (Float.valueOf(input).floatValue() > 0) {
					return (T) Float.valueOf(input);
				} else {
					throw new Exception();
				}
				
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
