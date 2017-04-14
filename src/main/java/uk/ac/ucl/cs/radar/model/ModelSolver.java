package uk.ac.ucl.cs.radar.model;
import java.util.List;
import java.util.Map;

/**
 * @author Saheed Busari and Emmanuel Letier
 */
public class ModelSolver {
	/**
	 * Solves the model and saves the analysis results. 
	 * @param m semantic model obtained during parsing.
	 * @return analysis result.
	 */
	public static AnalysisResult solve(Model m){

		// get all objectives
		List<Objective> objectives = m.getObjectives();
			
		// check for cylic dependencies in quality variables.
		try{
			m.getCyclicDependentVariables();
		}catch (Exception e){
			throw new RuntimeException (e.getMessage());
		}
		System.out.println("Finished checking cyclic dependecies");
		// get all decisions
		List<Decision> decisions = m.getDecisions();
		
		// instantiate the result object
		AnalysisResult result = new AnalysisResult(objectives,decisions);
		
		//long StartBeforeUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long start = System.currentTimeMillis();
		
		// get all solutions
		System.out.println("Generating design space.");
		List<Solution> allSolutions = m.getAllSolutions().list(); 
		
	
		result.addAllSolutions(allSolutions);
		// solution space
		result.addSolutionSpace(m.getSolutionSpace());

		// add subgraph obejective
		result.addSubGraphObejctive(m.getSubGraphObjective());
		
		// Evaluate objectives for all solutions
		System.out.println("Evaluation and computation of optimal solutions to begin");
		

		int i =0;
		for (Solution s: allSolutions){
			//result.addEvaluation(s, m.evaluate(objectives, s));	
			result.addEvaluation(s, new Simulator().evaluate(objectives, s,m));	
			i++;
		}
		
		// add -ve sign for maximisaiton
		Map<Solution, double[]> evaluatedSolutions = m.addMaximisationSign(result.getEvaluatedSolutions());
		
		// Shortlists Pareto-optimal solutions
		result.addShortlist(new Optimiser().getParetoSet(evaluatedSolutions, objectives));
		
		System.out.println("Optimal solutions computed");
		
		result.addNumberOfVariables(m.getQualityVariables().size());
		result.addNumberOfDecisions(m.getDecisions().size());
		
		// Computes Value of Information
		Objective infoValueObjective = m.getInfoValueObjective();
		List<String> paramNames = m.getParameters();
		List<Parameter> parameters = Model.getParameterList(paramNames, m);
		int nbrParam = parameters.size();
		System.out.println("Information value to be computed");
		if (infoValueObjective != null ){
			InformationValueAnalyser.computeInformationValue(result,infoValueObjective, result.getShortListSolutions(), parameters);;
		}
		
		long end = System.currentTimeMillis();
		long runTime = (end - start) / 1000;
		result.addRunTime(runTime);
		
		result.addNumberOfParameters(nbrParam);
		result.addSubGraphObejctive(m.getSubGraphObjective());
		result.addEviObjective(infoValueObjective);
		System.out.println("Information value computed");
		return result;
	}
	/**
	 * Solves the model and saves the analysis results. Used bu the Progress Bar to determine the status of the RADAR analysis. 
	 * @param m semantic model obtained during parsing.
	 * @return analysis result.
	 */
	public static AnalysisResult solve(Model m, AnalysisResult intermediateResult, int analysisIndex ){
		AnalysisResult result = null;
		if (intermediateResult == null){
			String message = "";
			List<Objective> objectives = m.getObjectives();
			List<Decision> decisions = m.getDecisions();
			result = new AnalysisResult(objectives,decisions);
			long cyclicCheckStartTime = System.currentTimeMillis();
			try{
				m.getCyclicDependentVariables();
			}catch (Exception e){
				throw new RuntimeException (e.getMessage());
			}
			long cyclicCheckEndTime = System.currentTimeMillis() -cyclicCheckStartTime;
			result.setConsoleMessage(message + "......   runtime["+cyclicCheckEndTime + " ms].\n\n" );
		}else{
			result = intermediateResult;
		}
		//design space generation
		if (analysisIndex == 0){
			String message = "";
			long designSpaceStartTime = System.currentTimeMillis();
			List<Solution> allSolutions = m.getAllSolutions().list();
			result.addAllSolutions(allSolutions);
			result.addSolutionSpace(m.getSolutionSpace());
			result.addSubGraphObejctive(m.getSubGraphObjective());
			long designSpaceEndTime = System.currentTimeMillis()-designSpaceStartTime ;
			result.addDesignSpaceRunTime(designSpaceEndTime/1000);
			message += "......   runtime["+ designSpaceEndTime + " ms].\n\n";
			result.setConsoleMessage(message);
		}
		// simulation
		if (analysisIndex == 1){
			String message = "";
			long simulationStartTime = System.currentTimeMillis();
			for (Solution s: result.getAllSolutions()){	
				result.addEvaluation(s, new Simulator().evaluate(result.getObjectives(), s,m));	
			}
			long simulationEndTime = System.currentTimeMillis()-simulationStartTime ;
			result.addSimulationRuntime(simulationEndTime/1000);
			message += "......   runtime[" + simulationEndTime +" ms].\n\n";
			result.setConsoleMessage(message);
		}
		// optimisation
		if (analysisIndex == 2){
			String message = "";
			long optimisationStartTime = System.currentTimeMillis();
			Map<Solution, double[]> evaluatedSolutions = m.addMaximisationSign(result.getEvaluatedSolutions());
			result.addShortlist(new Optimiser().getParetoSet(evaluatedSolutions, result.getObjectives()));
			result.addNumberOfVariables(m.getQualityVariables().size());
			result.addNumberOfDecisions(m.getDecisions().size());
			long optimisationEndTime = System.currentTimeMillis()-optimisationStartTime ;
			result.addOptimisationRuntime(optimisationEndTime/1000);
			message += "......   runtime["+ optimisationEndTime +" ms].\n\n";
			result.setConsoleMessage(message);
		}
		//information value Analysis
		if (analysisIndex == 3){
			String message = "";
			long InformationValueAnalysisStartTime = System.currentTimeMillis();
			Objective infoValueObjective = m.getInfoValueObjective();
			List<String> paramNames = m.getParameters();
			List<Parameter> parameters = Model.getParameterList(paramNames, m);
			int nbrParam = parameters.size();
			if (infoValueObjective != null ){
				InformationValueAnalyser.computeInformationValue(result,infoValueObjective, result.getShortListSolutions(), parameters);;
			}
			long InformationValueAnalysisEndTime = System.currentTimeMillis()-InformationValueAnalysisStartTime;
			result.addInformationValueRuntime(InformationValueAnalysisEndTime/1000);
			result.addNumberOfParameters(nbrParam);
			result.addSubGraphObejctive(m.getSubGraphObjective());
			result.addEviObjective(infoValueObjective);
			message+= "......   runtime["+ InformationValueAnalysisEndTime + " ms].\n\n";
			result.setConsoleMessage(message);
		}
		return result;
	}
}
