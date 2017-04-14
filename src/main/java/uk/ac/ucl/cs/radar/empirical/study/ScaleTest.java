package uk.ac.ucl.cs.radar.empirical.study;

import java.io.IOException;
import java.util.HashMap;

public class ScaleTest extends Experiment  {

	public static void main(String[] args) throws IOException, InterruptedException {
		ScaleTest exp = new ScaleTest();
	    exp.experimentName = "ScalabilityTesting";
		exp.map = new HashMap<String, Integer[]>();
		/**
		 * Specify the range of values for the number of simulation
		 */
		exp.map.put("Simulation", new Integer[]{10000, 10000});
		/**
		 * Specify the range of values for the number of model decisions.
		 */
		exp.map.put("Decision", new Integer[]{4, 4 });
		/**
		 * Specify the range of values for the number of options per decision.
		 */
		exp.map.put("Option", new Integer[]{3,3});
		/**
		 * Specify the  range of values for the number of model objectives
		 */
		exp.map.put("Objective", new Integer[]{2,2});
		/**
		 * Specify the  range of values for the number of model variables
		 */
		exp.map.put("Variable", new Integer[]{10,10});
		exp.modelName = "QuickTest";
		exp.modelType = "simple";
		exp.testResultName= "QuickTest";
		//exp.output = args[0];
		exp.output = "/Users/INTEGRALSABIOLA/Documents/JavaProject";
		exp.outputPath = exp.output + "/" + exp.modelName + "/AnalysisResult/" ;
		exp.maxRunTimeMilliSeconds = 1800000L;
		//exp.maxRunTimeMilliSeconds = Long.parseLong(args[1]);
	    int numberOfThreads =1 ;
	    exp.runExperiment(numberOfThreads) ;
	}

}
