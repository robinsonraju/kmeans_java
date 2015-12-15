package cs286;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import edu.sjsu.cs.rr.kmeans.Centroid;
import edu.sjsu.cs.rr.kmeans.Centroid.InitMethod;
import edu.sjsu.cs.rr.kmeans.Distance;
import edu.sjsu.cs.rr.kmeans.Distance.DistanceType;
import edu.sjsu.cs.rr.kmeans.KmeansInput;
import edu.sjsu.cs.rr.kmeans.KmeansInput.InputType;

/**
 * Class to cluster data using KMeans
 * 
 * @author rraju
 *
 */
public class Kmeans {

	/**
	 * The program would have the following syntax java 
	 * -cp kmeans.jar cs286.Kmeans <k> <max-iterations> <delta> <dist> <init> <input> <output>
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

	/**
	 * validateInputData 
	 * 
	 * @param args
	 * @return KmeansInput
	 * @throws Exception
	 */
	public KmeansInput validateInputData (String[] args) throws Exception {
		System.out.println("**** Reading Input parameters ****");

		int k = KmeansInput.getInput(args[0], InputType.K, new Integer(0));

		int maxIter = KmeansInput.getInput(args[1], InputType.MAX_ITER, new Integer(0));
		
		float delta = KmeansInput.getInput(args[2], InputType.DELTA, new Float(0)).floatValue();
		
		DistanceType dist = (DistanceType) KmeansInput.getInput(args[3], InputType.DISTANCE, new Object());
		
		InitMethod init = (InitMethod) KmeansInput.getInput(args[4], InputType.INIT, new Object());

		File inputFile = (File) KmeansInput.getInput(args[5], InputType.INPUT_FILE,new Object());
		
		File outputFile = (File) KmeansInput.getInput(args[6], InputType.OUTPUT_FILE,new Object());
		
		// int featureCount = getInput(args[7], InputType.FEATURE_COUNT, new Integer(0));
		int featureCount = 4; 
		
		System.out.println("Input is valid.");
		return new KmeansInput(k, maxIter, delta, dist, init, inputFile, outputFile, featureCount);
		
	}
	

	/**
	 * Cluster data
	 * @param input
	 * @throws Exception
	 */
	public void clusterData (KmeansInput input) throws Exception {
		System.out.println("**** Starting KMeans ****");
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
		
		System.out.println("** Storing input into a matrix **");
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
        System.out.println("** Computing centroids using init method : " + input.getInit().name() +"**");
        double centroid[][] = Centroid.getCentroids(dataSet, k, input.getInit());

        System.out.println("** Computing distances using distance method : " + input.getDist().name() +"**");
        byte[][] cluster = new byte[k][dataCount];
        
        boolean notWithinDelta;
        int numInterations = 0;
        while(numInterations < input.getMaxIter()) {
        	numInterations++;
            notWithinDelta = true;
            
            //calculating pairwise distance from the centroids
            double tempDataSet[][] = new double[k][dataCount];
            for(int i = 0; i < k; ++i) {
            	for(int j = 0; j < dataCount; ++j) {
            		tempDataSet[i][j] = Distance.getDistance(centroid[i], dataSet[j], input.getDist());
                }
            }
            
            byte groupData[][] = new byte[k][dataCount];
            for(int i = 0; i < dataCount; ++i) {
                double min = 9999999;
                int r = 0, c = 0;
                for(int j = 0; j < k ; ++j) {
                    if(tempDataSet[j][i] < min) {
                        min = tempDataSet[j][i];
                        r = j;
                        c = i;
                    }
                }
                groupData[r][c] = 1; 
            }

            //checking whether the groupData is same as the previous finalResult
            for(int i = 0; i < k; ++i ) {
                for(int j = 0; j < dataCount; ++j) {
                	if(groupData[i][j] != cluster[i][j]) {
                        notWithinDelta = false;
                        break;
                    }
                }
            }
            //updating the finalResult by new groupData
            cluster = groupData;
            if(notWithinDelta)
                break;
            for(int i = 0; i < k; ++i) {
                int count = 0;
                double temp[] = new double[featureCount];
                for(int j = 0; j < dataCount; ++j) {
                    if(groupData[i][j] == 1) {
                        count++;
                        for(int m = 0; m < featureCount; ++m) {
                            temp[m] += dataSet[j][m];
                        }
                    }
                }
                //updating centroids
                for(int n = 0; n < featureCount; ++n) {
                    centroid[i][n] = temp[n] / count;
                }
                temp = null;
            }
            tempDataSet = null;
            groupData = null;
        }
        
        System.out.println("**** Printing output to file" + input.getOutputFile().getAbsolutePath() + "****");
        List<String> outputLines = new ArrayList<String>();
        outputLines.add("k = " + input.getK());
        outputLines.add("distance = " + input.getDist().name());
        

        for(int i = 0; i < k; ++i){
            String centroidOutStr = "centroid " + (i + 1) + " = [";
            for(int j = 0; j < dataCount; ++j) {
                if(cluster[i][j] == 1) {
                	centroidOutStr += (j + 1) + ", ";
                }
            }
            centroidOutStr += centroidOutStr.substring(0,centroidOutStr.length()-2) + "]";
            outputLines.add(centroidOutStr);
        }
        
        // Mean Intercluster distance
        double sumDist = 0.0;
        for (int i = 0; i < centroid.length; i++) {
        	for (int j = 0; j < centroid.length; j++) {
        		sumDist += Distance.computeEuclideanDistance(centroid[i], centroid[j]);
        	}
		}
        outputLines.add("Mean intercluster distance = " + sumDist/Math.pow(centroid.length, 2));
        
        // Mean Intra cluster distance 
        double interClustDistAll = 0.0;
        for(int i = 0; i < k; ++i){
	        double interClustDist = 0.0;
	        for(int j = 0; j < dataCount; ++j) {
	            if(cluster[i][j] == 1) {
	            	interClustDist += Distance.computeEuclideanDistance(centroid[i], dataSet[j]);
	            }
	        }
	        interClustDistAll += interClustDist;
	    }
        outputLines.add("Mean intracluster distance = " + interClustDistAll/dataCount);
        
        Path file = Paths.get(input.getOutputFile().getAbsolutePath());
        Files.write(file, outputLines, Charset.forName("UTF-8"));
        
	}
}
