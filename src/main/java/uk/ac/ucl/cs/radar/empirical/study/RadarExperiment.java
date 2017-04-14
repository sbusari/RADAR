
package uk.ac.ucl.cs.radar.empirical.study;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import uk.ac.ucl.cs.radar.model.AnalysisData;
import uk.ac.ucl.cs.radar.model.AnalysisResult;
import uk.ac.ucl.cs.radar.model.Model;
import uk.ac.ucl.cs.radar.model.ModelSolver;
import uk.ac.ucl.cs.radar.model.Parser;
import uk.ac.ucl.cs.radar.model.ScatterPlot3D;
import uk.ac.ucl.cs.radar.model.SyntheticModelGenerator;
import uk.ac.ucl.cs.radar.model.TwoDPlotter;
import uk.ac.ucl.cs.radar.userinterface.InputValidator;
import uk.ac.ucl.cs.radar.utilities.Helper;

public class RadarExperiment extends Thread {
	int min_nbr_variables;
	int nbr_objectives;
	int min_nbr_options;
	int max_nbr_options;
	int nbr_decisions;
	String modelName = "Synthetic";
	String testResultName = "ScalabilityTest";
	String output;
	String outputPath; 
	String modelResultPath; 
	String modelType = "simple";	
	int nbr_Simulation;
	String infoValueObjective = "OF0";
	String subGraphObjective = "OF0";
	Random rn = new Random();
	AnalysisResult analysis_result;
	Map<String, Integer> param = new HashMap<String, Integer>();
	
	int getRandom (int min, int max){
		
		int range = max - min + 1;
		int num =  rn.nextInt(range) + min;
		return num;
	}
	public RadarExperiment(String modelName, String modelType, String output, String testResultName, Map<String, Integer> param){
		this.modelName = modelName;
		this.modelType = modelType;
		this.output = output;
		this.outputPath = output + "/" + modelName + "/AnalysisResult/" ;
		this.modelResultPath = output + "/" + modelName + "/AnalysisResult/" ;
		this.param = param;
		this.testResultName = testResultName;
	}
	public void run() {
		Integer simulation = param.get("Simulation" );
		Integer decision = param.get("Decision");
		Integer option = param.get("Option");
		Integer variable = param.get("Variable");
		Integer objective = param.get("Objective");
		
		try {
			String experimentParam ="";
			if(Math.pow(min_nbr_options, nbr_decisions ) < Integer.MAX_VALUE){
				min_nbr_options = option;
				max_nbr_options = min_nbr_options;
				min_nbr_variables = variable;
				nbr_decisions = decision;
				nbr_objectives = objective;
				nbr_Simulation = simulation;
				experimentParam = "Param: " + "Obj(" + nbr_objectives + ")-O(" + min_nbr_options + ")-D(" + nbr_decisions + ")-S(" + nbr_Simulation +")";
				String analysis_runtimes = generateAndAnalyseRadarModel(nbr_Simulation, experimentParam );
				System.out.println("Param: "+ experimentParam + ", "+ analysis_runtimes);
				Helper.printResults (outputPath, experimentParam + "," + analysis_result.getSolutionSpace() + ","+ analysis_result.getAllSolutions().size()+ "," +analysis_runtimes , testResultName +".csv", true);
			}else{
				Helper.printResults (outputPath, experimentParam + ","+ Math.pow(min_nbr_options, nbr_decisions) +","+ "Design Space, Design Space Time, Simulation Time, Optimisation Time, Information Value Time, Total time \n", testResultName +".csv", true);

			}							
						
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

	AnalysisData populateExperimentData () throws Exception{
		AnalysisData result = new AnalysisData();
		InputValidator.validateOutputPath(output);
		// populate data
		result.setSimulationNumber(nbr_Simulation);
		if (output.trim().charAt(output.length()-1) != '/'){
			result.setOutputDirectory(output.trim() +"/");
		}
		return result;
		
	}
	Integer [] getArrayElements (Integer [] arrayRange){
		List<Integer> result = new ArrayList<Integer>();
		for (int i = arrayRange[0]; i <= arrayRange[1]; i++){
			result.add(i);
		}
		return result.toArray(new Integer [result.size()]);
	}
	Model loadModel (String resultpath, String modelName) throws Exception{
		Model semanticModel =null;
		String testmodel = null;
		SyntheticModelGenerator smg = new SyntheticModelGenerator();
		smg.setMinNbrOptions(min_nbr_options);
		smg.setMaxNbrOptions(max_nbr_options);
		smg.setMinNbrVariables(min_nbr_variables);
		smg.setModelName(modelName);
		smg.setNbrDecisions(nbr_decisions);
		smg.setNbrObjectives(nbr_objectives);
		testmodel = smg.generate(modelType);
		Helper.printResults (resultpath , testmodel, modelName +".rdr", false);
		System.out.print(testmodel);
		InputValidator.validateOutputPath(output);
		try {
			semanticModel = new Parser().parseModel(testmodel, nbr_Simulation, infoValueObjective,subGraphObjective);
		}catch (RuntimeException re){
			throw new RuntimeException( "Error: "+ re.getMessage());
		}
		return semanticModel;
	}
	String generateAndAnalyseRadarModel (int simulation, String expID){
		String runtime = "";
		try {
    		// populate model and algorithm data
    		AnalysisData dataInput = populateExperimentData();
    		
    		// get sematic model from model file
    		Model semanticModel = loadModel (modelResultPath + expID + "/", dataInput.getProblemName());
    		semanticModel.setNbr_Simulation(simulation);
    		
    		// update experiemnt data with semantic model and information value objective.
    		dataInput.setProblemName(semanticModel.getModelName());
    		InputValidator.objectiveExist(semanticModel, infoValueObjective);
    		InputValidator.objectiveExist(semanticModel, subGraphObjective);
    		
    		// analyse model
    		analysis_result = ModelSolver.solve(semanticModel);
    		
			String analysisResult = analysis_result.analysisToString();
			String analysisResultToCSV = analysis_result.analysisResultToCSV();
			Helper.printResults (modelResultPath , analysisResult, semanticModel.getModelName() +expID +".out", false);
			Helper.printResults (modelResultPath , analysisResultToCSV, semanticModel.getModelName() +expID +".csv", false);
			runtime = analysis_result.analysisRuntimeAndMemoryToCSV();
			
			
			// generate graphs
			boolean generatePlots = false;
			if (generatePlots){
				String decisionGraph = semanticModel.generateDecisionDiagram(analysis_result.getAllSolutions());
				Helper.printResults (modelResultPath + "graph/", decisionGraph, dataInput.getProblemName() + "dgraph.dot", false);
			}
			if (generatePlots){
				String variableGraph = semanticModel.generateDOTRefinementGraph(semanticModel, analysis_result.getSubGraphObjective());
				Helper.printResults (modelResultPath + "graph/", variableGraph,  dataInput.getProblemName() + "vgraph.dot", false);
			}
    		if (generatePlots){
    			if (analysis_result.getShortListObjectives().get(0).length == 2){
					TwoDPlotter twoDPlot = new TwoDPlotter();
					twoDPlot.plot(semanticModel,modelResultPath, analysis_result);
				}else if (analysis_result.getShortListObjectives().get(0).length == 3){
					ScatterPlot3D sc3D= new ScatterPlot3D( );
					sc3D.plot(semanticModel, modelResultPath, analysis_result);;
;				}
    		}
    		System.out.println("Finished!");
    		
    	}catch (Exception e){
    		System.out.print("Error: ");
    		System.out.println(e.getMessage());
    	}
		return runtime;
	}
	

}

