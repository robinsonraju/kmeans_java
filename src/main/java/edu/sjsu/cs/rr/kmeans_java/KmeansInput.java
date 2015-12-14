package edu.sjsu.cs.rr.kmeans_java;

import java.io.File;

import edu.sjsu.cs.rr.kmeans_java.Kmeans.Distance;
import edu.sjsu.cs.rr.kmeans_java.Kmeans.InitMethod;

public class KmeansInput {
	private final int k;
	private final int maxIter;
	private final float delta;
	private final Distance dist; 
	private final InitMethod init; 
	private final File inputFile;
	private final File outputFile;
	private final int featureCount;

	public KmeansInput(int k, 
			int maxIter, 
			float delta,
			Distance dist, 
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

	public int getK() {
		return k;
	}

	public int getMaxIter() {
		return maxIter;
	}

	public float getDelta() {
		return delta;
	}

	public Distance getDist() {
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
