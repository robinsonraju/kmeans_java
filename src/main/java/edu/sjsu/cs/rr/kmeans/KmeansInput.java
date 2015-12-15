package edu.sjsu.cs.rr.kmeans;

import java.io.File;
import java.io.FileNotFoundException;

import edu.sjsu.cs.rr.kmeans.Centroid.InitMethod;
import edu.sjsu.cs.rr.kmeans.Distance.DistanceType;

public class KmeansInput {
	private final int k;
	private final int maxIter;
	private final float delta;
	private final DistanceType dist; 
	private final InitMethod init; 
	private final File inputFile;
	private final File outputFile;
	private final int featureCount;
	
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

	public KmeansInput(int k, 
			int maxIter, 
			float delta,
			DistanceType dist, 
			InitMethod init, 
			File input, 
			File output,
			int featureCount) {
		this.k = k;
		this.maxIter = maxIter;
		this.delta = delta;
		this.dist = dist;
		this.init = init;
		this.inputFile = input;
		this.outputFile = output;
		this.featureCount = featureCount;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getInput(String input, InputType inputType,
			T outputType) throws Exception {
		
		try {
			switch (inputType) {
			case K:
				Integer k =  Integer.valueOf(input);
				if (k > 0) {
					return (T) k;
				} else {
					throw new Exception();
				}
				
			case MAX_ITER:
				return (T) Integer.valueOf(input);
			
			case DELTA:
				if (Float.valueOf(input).floatValue() > 0) {
					return (T) Float.valueOf(input);
				} else {
					throw new Exception();
				}
				
			case DISTANCE:
				return (T) DistanceType.valueOf(input);
				
			case INIT:
				return (T) InitMethod.valueOf(input);
				
			case INPUT_FILE:
				return (T) checkFile(input);
			
			case OUTPUT_FILE:
				if (notNullOrEmpty(input) && !fileExists(input)) {
					return (T) new File(input);	
				} else {
					throw new Exception();
				}
				
			case FEATURE_COUNT:
				return (T) Integer.valueOf(input);

			default:
				throw new Exception("Invalid Input : " + input + " " + inputType.getInputTypeStr());	
			}
		} catch (Exception e) {
			throw new Exception("Invalid Input : " + input + " " + inputType.getInputTypeStr() , e);
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
	
	/**
	 * 
	 * @param filePathString
	 * @return boolean
	 */
	private static boolean fileExists(String filePathString){
		File f = new File(filePathString);
		return f.exists() && !f.isDirectory();
	}
	
	public int getK() {
		return k;
	}

	public int getMaxIter() {
		return maxIter;
	}

	public float getDelta() {
		return delta;
	}

	public DistanceType getDist() {
		return dist;
	}

	public InitMethod getInit() {
		return init;
	}

	public File getInputFile() {
		return inputFile;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public int getFeatureCount() {
		return featureCount;
	}

	@Override
	public String toString() {
		return "KmeansInput [k=" + k + ", maxIter=" + maxIter + ", delta="
				+ delta + ", dist=" + dist + ", init=" + init + ", input="
				+ inputFile + ", output=" + outputFile + ", featureCount="
				+ featureCount + "]";
	}
}
