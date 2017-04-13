package uk.ac.ucl.cs.radar.userinterface;

import uk.ac.ucl.cs.radar.model.AnalysisResult;
import uk.ac.ucl.cs.radar.model.AnalysisData;
import uk.ac.ucl.cs.radar.model.Model;
import uk.ac.ucl.cs.radar.model.ModelSolver;
import uk.ac.ucl.cs.radar.model.Parser;
import uk.ac.ucl.cs.radar.model.ScatterPlot3D;
import uk.ac.ucl.cs.radar.model.TwoDPlotter;
import uk.ac.ucl.cs.radar.utilities.Helper;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class RADAR_CMD {
	
	// how to exit the command.
	@Parameter(names = "--debug", description = "Debug mode")
	public boolean debug = false;
	
	@Parameter(names = "--help", description = "Help")
	private boolean help = false;

	@Parameter(names = "--output", description = "Output folder where the results are saved. Input <file path>.  ")
	public String output = null;
	
	@Parameter(names = "--model", description = "The file that contains the decision model. Input <file path>. ")
	public String model = null;
	
	@Parameter(names = "--parse", description = "Parses the decision model to check for syntax error.")
	private boolean parse = true;
	
	
	@Parameter(names = "--solve", description = "Solves the decision model using exhaustive search. ")
	public boolean solve = true;
	
	@Parameter(names = "--decision", description = "Generates the model decision dependency graph.")
	public boolean decision = true;
	
	@Parameter(names = "--variable", description = "Generates the model AND/OR variable depenedency graph.")
	public boolean variable = true;
	
	
	@Parameter(names = "--nbr_simulation", description = "Number of simulation run. Input <sample size> .")
	public Integer nbr_Simulation = 10000;
	
	@Parameter(names = "--problem-name", description = "Name of the experiment. Input <experiment name>  ")
	public String expName = "Testing";
		
	// a list needs an arity, but because we do not know the arity we enforce every thing in a quoted string separated by comma.
	@Parameter(names ="--infoValueObjective", description = "Computes evtpi and evppi. Input is an objective name. An example usage of this command is: --infoValueObjective 'FinancialLoss' ")
	private String infoValueObjective = "ENB";
	
	@Parameter(names ="--subGraphObjective", description = "Generates AND/OR subgraph for the specified objective only. Input is an objective name. An example usage of this command is: --subGraphObjective 'InvestigationCost' ")
	private String subGraphObjective = "ENB";
	
	
	@Parameter(names ="--pareto", description = "Displays the pareto plots.")
	public boolean pareto = false;
	
   
	public static void main(String[]args) throws Exception {
    	RADAR_CMD cmd = new RADAR_CMD();
    	JCommander jcommander = new JCommander(cmd, args);
    	jcommander.setProgramName("Radar");
        if (cmd.help) {
        	jcommander.usage();
            return;
        }
        cmd.run(cmd);
    }

	AnalysisData populateExperimentData () throws Exception{
		AnalysisData result = new AnalysisData();
		InputValidator.validateModelPath(model);
		InputValidator.validateOutputPath(output);
		// populate data
		result.setSimulationNumber(nbr_Simulation);
		if (output.trim().charAt(output.length()-1) != '/'){
			result.setOutputDirectory(output.trim() +"/");
		}
		return result;
		
	}
	Model loadModel () throws Exception{
		//4. when parse is specified quickly parse and write a message that the model is parsed.
		Model semanticModel =null;
		if (parse == true || solve == true){
			InputValidator.validateModelPath(model);
    		InputValidator.validateOutputPath(output);
    		try {
    			semanticModel = new Parser().parseCommandLineModel(model.trim(), nbr_Simulation, infoValueObjective,subGraphObjective);
    		}catch (RuntimeException re){
    			throw new RuntimeException( "Error: "+ re.getMessage());
    		}
    		
		}
		// if solve is specified, we  parse the model and solve.
		if (semanticModel == null && solve == true){
			semanticModel = new Parser().parseCommandLineModel(model.trim(), nbr_Simulation, infoValueObjective,subGraphObjective);
		}
		if (model != null && output != null && semanticModel == null){
			throw new RuntimeException ("Specify a command to solve the model. Use the --help command for more information.");
		}
		return semanticModel;
		
	}
	void analyseRadarModel (int nbr_simulation){
		try {
    		// populate model and algorithm data
    		AnalysisData dataInput = populateExperimentData();
    		// get sematic model from model file
    		Model semanticModel = loadModel ();
    		
    		semanticModel.setNbr_Simulation(nbr_simulation);
    		
    		// update experiemnt data with semantic model and information value objective.
    		dataInput.setProblemName(semanticModel.getModelName());
    		InputValidator.objectiveExist(semanticModel, infoValueObjective);
    		InputValidator.objectiveExist(semanticModel, subGraphObjective);
    		
    		String modelResultPath = dataInput.getOutputDirectory() + dataInput.getProblemName() + "/ICSE/AnalysisResult/" + nbr_simulation +"/";
    		// analyse model
    		AnalysisResult result = ModelSolver.solve(semanticModel);
    		
			String analysisResult = result.analysisToString();
			String analysisResultToCSV = result.analysisResultToCSV();
			Helper.printResults (modelResultPath , analysisResult, dataInput.getProblemName() +".out", false);
			Helper.printResults (modelResultPath , analysisResultToCSV, semanticModel.getModelName() +".csv", false);
			
			// generate graphs
			if (decision == true){
				String decisionGraph = semanticModel.generateDecisionDiagram(result.getAllSolutions());
				Helper.printResults (modelResultPath + "graph/", decisionGraph, dataInput.getProblemName() + "dgraph.dot", false);
			}
			
			if (variable == true){
				String variableGraph = semanticModel.generateDOTRefinementGraph(semanticModel, result.getSubGraphObjective());
				Helper.printResults (modelResultPath + "graph/", variableGraph,  dataInput.getProblemName() + "vgraph.dot", false);
			}
			
    		if (pareto == true){
    			if (result.getShortListObjectives().get(0).length == 2){
					TwoDPlotter twoDPlot = new TwoDPlotter();
					twoDPlot.plot(semanticModel,modelResultPath, result);
				}else if (result.getShortListObjectives().get(0).length == 3){
					ScatterPlot3D sc3D= new ScatterPlot3D( );
					sc3D.plot(semanticModel, modelResultPath, result);;
;				}
    		}
    		System.out.println("Finished!");
    		
    	}catch (Exception e){
    		System.out.print("Error: ");
    		System.out.println(e.getMessage());
    	}
	}
	public void run(RADAR_CMD cmdParam) throws Exception {
		if (solve == false && parse == true){
			Model semanticModel = loadModel ();
			System.out.println("Model was parsed succesfully.");
		}else if (solve == true){
			Integer [] simulations = {10000};
			for (int i =0; i < simulations.length; i++){
				nbr_Simulation = simulations[i];
				analyseRadarModel (nbr_Simulation);
			}
			//analyseRadarModel (nbr_Simulation);
		}
		
    }

	
}
