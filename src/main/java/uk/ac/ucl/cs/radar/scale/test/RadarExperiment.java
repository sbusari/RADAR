
package uk.ac.ucl.cs.radar.scale.test;

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
	int min_nbr_variables_;
	int nbr_objectives_;
	int min_nbr_options_;
	int max_nbr_options_;
	int nbr_decisions_;
	String modelName_ = "Test";
	String testResultName_ = "ScaleTest";
	String output_;
	String outputPath; 
	String modelResultPath; 
	String modelType_ = "simple";	
	int nbr_Simulation;
	String infoValueObjective = "OF0";
	String subGraphObjective = "OF0";
	Random rn = new Random();
	AnalysisResult analysis_result;
	Map<String, Integer> param_ = new HashMap<String, Integer>();
	
	int getRandom (int min, int max){
		
		int range = max - min + 1;
		int num =  rn.nextInt(range) + min;
		return num;
	}
	public RadarExperiment(String modelName, String modelType, String output, String testResultName, Map<String, Integer> param){
		modelName_ = modelName;
		modelType_ = modelType;
		output_ = output;
		outputPath = output + "/" + modelName + "/ICSE/AnalysisResult/" ;
		modelResultPath = output + "/" + modelName + "/ICSE/AnalysisResult/" ;
		param_ = param;
		testResultName_ = testResultName;
	}
	public void run() {
		Integer simulation = param_.get("Simulation" );
		Integer decision = param_.get("Decision");
		Integer option = param_.get("Option");
		Integer variable = param_.get("Variable");
		Integer objective = param_.get("Objective");
		
		try {
			String experimentParam ="";
			if(Math.pow(min_nbr_options_, nbr_decisions_ ) < Integer.MAX_VALUE){
				min_nbr_options_ = option;
				max_nbr_options_ = min_nbr_options_;
				min_nbr_variables_ = variable;
				nbr_decisions_ = decision;
				nbr_objectives_ = objective;
				nbr_Simulation = simulation;
				experimentParam = "Param: " + "Obj(" + nbr_objectives_ + ")-O(" + min_nbr_options_ + ")-D(" + nbr_decisions_ + ")-S(" + nbr_Simulation +")";
				String analysis_runtimes = generateAndAnalyseRadarModel(nbr_Simulation, experimentParam );
				System.out.println("Param: "+ experimentParam + ", "+ analysis_runtimes);
				Helper.printResults (outputPath, experimentParam + "," + analysis_result.getSolutionSpace() + ","+ analysis_result.getAllSolutions().size()+ "," +analysis_runtimes , testResultName_ +".csv", true);
			}else{
				Helper.printResults (outputPath, experimentParam + ","+ Math.pow(min_nbr_options_, nbr_decisions_ ) +","+ "Design Space, Design Space Time, Simulation Time, Optimisation Time, Information Value Time, Total time \n", testResultName_ +".csv", true);

			}							
						
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

	AnalysisData populateExperimentData () throws Exception{
		AnalysisData result = new AnalysisData();
		InputValidator.validateOutputPath(output_);
		// populate data
		result.setSimulationNumber(nbr_Simulation);
		if (output_.trim().charAt(output_.length()-1) != '/'){
			result.setOutputDirectory(output_.trim() +"/");
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
		//4. when parse is specified quickly parse and write a message that the model is parsed.
		Model semanticModel =null;
		String testmodel = null;
		SyntheticModelGenerator smg = new SyntheticModelGenerator();
		smg.setMinNbrOptions(min_nbr_options_);
		smg.setMaxNbrOptions(max_nbr_options_);
		smg.setMinNbrVariables(min_nbr_variables_);
		smg.setModelName(modelName_);
		smg.setNbrDecisions(nbr_decisions_);
		smg.setNbrObjectives(nbr_objectives_);
		testmodel = smg.generate(modelType_);
		Helper.printResults (resultpath , testmodel, modelName +".radar", false);
		System.out.print(testmodel);
		InputValidator.validateOutputPath(output_);
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
			//Helper.printResults (modelResultPath , analysisResult, dataInput.getProblemName() +".out", false);
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

