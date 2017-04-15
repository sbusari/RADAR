package uk.ac.ucl.cs.radar.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import uk.ac.ucl.cs.radar.exception.CyclicDependencyException;
import uk.ac.ucl.cs.radar.exception.ModelException;
import uk.ac.ucl.cs.radar.information.analysis.InformationAnalysis;
/**
 * @author Saheed Busari and Emmanuel Letier
 * This class is the semantic model obtained from parsing the decision model. 
 */
public class Model implements ModelVisitorElement {
	
	public Model (){
		objectives = new LinkedHashMap<String, Objective>();
		qualityVariables = new LinkedHashMap<String, QualityVariable>();
		parameters =  new ArrayList<String>();
		decisions = new LinkedHashMap<String, Decision>();
		visitedQualityVariableStack = new LinkedHashMap<String,QualityVariable>();
		undefinedQualityVariable = new ArrayList<String>();
	}
	/**
	 * the name of the model instance
	 */
	private String modelName;
	/**
	 * A map that contains the model objectives
	 */
	private Map<String, Objective> objectives;
	/**
	 * A map that contains the model varibales
	 */
	private Map<String, QualityVariable> qualityVariables;
	/**
	 * the list of model parameter names
	 */
	private List<String> parameters;
	/**
	 * A map that contains the model decisions.
	 */
	private Map<String, Decision> decisions;
	/**
	 * the information value objective used to compute evtpi and evppi
	 */
	private Objective infoValueObjective;
	/**
	 * the model objective for which the generated goal-graph starts from
	 */
	private Objective subgraphObjective;
	/**
	 * A variable for which a generated goal-graph starts from
	 */
	private String subgraphVariable;
	
	/**
	 * the number of scenarios during monte-carlo simulation
	 */
	private int noOfSimulation_;
	/**
	 * A map that contains the visited quality variable when traversing a model. It is used to ensure there is no cyclic dependency in the model 
	 */
	private Map<String, QualityVariable> visitedQualityVariableStack;
	/**
	 * A list that stores model variables used within an expression but never defined.
	 */
	private List<String> undefinedQualityVariable;
	
	public void setModelName(String modelName ){
		this.modelName =modelName;
	}
	
	public String getModelName(){
		return modelName;
	}
	/**
	 * Adds quality variable to the list of quality variables.
	 * @param qvName quality variable name.
	 * @param qualityVariable the quality variable instance to add.
	 */
	public void addQualityVariable(String qvName, QualityVariable qualityVariable){
		qualityVariables.put(qvName, qualityVariable);
	}
	/**
	 * @return a map that stores the model quality variables, where the map key is the quality variable name and the map value is a quality variable
	 */
	public Map<String, QualityVariable> getQualityVariables(){
		return qualityVariables;
	}
	/**
	 * Adds model parameter to the list of model parameters.
	 * @param paramName quality variable that is a distribution.
	 */
	public void addParameters(String paramName){
		if (!parameters.contains(paramName)){
			parameters.add(paramName);
		}
	}
	/**
	 * Adds quality variable to the list of visited quality variables when checking for cyclic dependencies between quality variables.
	 * @param qv quality variable to add.
	 */
	public void addVisitedQualityVariable (QualityVariable qv){
		visitedQualityVariableStack.put(qv.getLabel(), qv);
	}
	/**
	 * @return all visited quality variable.
	 */
	public Map<String, QualityVariable> getVisitedQualityVariable (){
		return visitedQualityVariableStack;
	}
	/**
	 * @return a list of model paramters i.e paramters that are distribution.
	 */
	public List<String> getParameters(){
		return parameters;
	}
	/**
	 * Adds objective instance to the list of objectives.
	 * @param objName name of objective.
	 * @param objective the objective instance to add.
	 */
	public void addObjective(String objName, Objective objective){
		objectives.put(objName, objective);
	}
	/**
	 * @return a list that stores the model objectives.
	 */
	public List<Objective> getObjectives (){
		return new ArrayList<Objective>(objectives.values());
	}
	/**
	 * Sets model decisions .
	 * @param decision a map whose keys are the name of decision and values are Decision.
	 */
	public void setDecisions(Map<String, Decision> decision){
		this.decisions = decision;
	}
	/**
	 * @return a list that stores the model decisions.
	 */
	public List<Decision> getDecisions (){
		return new ArrayList<Decision>(decisions.values());
	}
	/**
	 * Sets the information value objective.
	 * @param objective information value objective to add.
	 */
	public void setInfoValueObjective (Objective objective){
			this.infoValueObjective = objective;
	}
	/**
	 * @return  information value objective.
	 */
	public Objective getInfoValueObjective (){
		return infoValueObjective ;
	}
	/**
	 * Sets the subgraph objective used to restrict the genration of a goal graph to a particular objective.
	 * @param subGraphObj subgraph objective to add.
	 */
	public void setSubGraphObjective (Objective subGraphObj){
		subgraphObjective = subGraphObj;
	}
	/**
	 * @return  subgraph objective used to restrict the genration of a goal graph to a particular objective.
	 */
	public Objective getSubGraphObjective (){
		return subgraphObjective ;
	}
	public void setSubgraphVariable(String subgraphVar){
		this.subgraphVariable = subgraphVar;
	}
	public String getSubgraphVariable(){
		return subgraphVariable;
	}
	/**
	 * Sets the number of simulation for monte-carlo simulation.
	 * @param noOfSimulation number of simulation.
	 */
	public void setNbr_Simulation(int noOfSimulation) {
		noOfSimulation_ = noOfSimulation;
	}
	/**
	 * @return  the number of simulation for monte-carlo simulation.
	 */
	public int getNbr_Simulation() {
		return noOfSimulation_;
	}
	/**
	 * @return the number of all possible solutions for the decision model.
	 */
	public long getSolutionSpace(){
		long result =1;
		for (Map.Entry<String , Decision> entry: this.decisions.entrySet()){
			result *= entry.getValue().getOptions().size();
		}
		return result;
	}
	/**
	 * Evaluates model objective through monte-carlo simulation.
	 * @param s a solution to be simulated through monte-carlo simulation.
	 * @param objectives model objectives to simulate
	 * @return an array of simulated objective values.
	 */
	public double[] evaluate (List<Objective> objectives, Solution s){
		s.setSemanticModel(this);
		double [] objectiveValues = new double [objectives.size()];
		for (int i =0; i < objectives.size(); i ++){
			Objective obj = objectives.get(i);
			double value = obj.evaluate(s);
			objectiveValues[i] = value;
		}
		return objectiveValues;
	}
	/**
	 * Computes and store expected value of perfect information (evtpi) and expected value of partial perfect information (evppi) in the AnalysisResult  object.
	 * @param result analysis result.
	 * @param objective model objectives.
	 * @param solutions list of optimal solutions
	 * @param params list of model parameters.
	 */
	public void computeInformationValue(AnalysisResult result, Objective objective, List<Solution> solutions, List<Parameter> params){
		result.addEviObjective (objective);
		double[][] objSim = objective.getQualityVariable().simulate(solutions);
		// compute evtpi
		double evtpi = InformationAnalysis.evpi(objSim);
		result.addEVTPI(evtpi);
		// compute evppi for each quality variable in params
		for (int i=0; i <params.size(); i++){
			Parameter param = params.get(i) ;
	        double[] paramSim = param.getSimulationData();
	        double evppi = InformationAnalysis.evppi(paramSim, objSim);
	        result.addEVPPI(param.getLabel(), evppi);
		}
	}
	/**
	 * @param paramNames list of model parameters.
	 * @param m semantic model obtained from parsing.
	 * @return a list of model parameters
	 */
	public static List<Parameter> getParameterList (List<String> paramNames, Model m){
		List<Parameter> parameters = new ArrayList<Parameter>();
		for (int i =0; i < paramNames.size(); i ++){
			QualityVariable qv = m.getQualityVariables().get(paramNames.get(i));			
			if (qv.getDefinition() instanceof Parameter &&  !(((Parameter)qv.getDefinition()).getDistribution() instanceof DeterministicDistribution) ){
				Parameter value = (Parameter)qv.getDefinition();
				value.setLabel(qv.getLabel());
				parameters.add(value);
			}else if (qv.getDefinition() instanceof OR_Refinement){
				Map<String, AND_Refinement> optionsExpr = ((OR_Refinement)qv.getDefinition()).getDefinition();
				for (Map.Entry<String, AND_Refinement> entry: optionsExpr.entrySet()){
					if (entry.getValue().getDefinition() instanceof Parameter && !(((Parameter)entry.getValue().getDefinition()).getDistribution() instanceof DeterministicDistribution)){
						Parameter value = (Parameter)entry.getValue().getDefinition();
						value.setLabel(qv.getLabel() + "[" +entry.getKey() + "]");
						parameters.add(value);
					}
				}
			}
		}
		return parameters;
	}
	/**
	 * @return a list of generated minimal set of solutions.
	 */
	public  SolutionSet getAllSolutions(){
		SolutionSet result = new SolutionSet();
		for (Objective obj: this.getObjectives()){
			SolutionSet solnSet = obj.getAllSolutions(this);
			result = result.merge(solnSet);
		}
		return result;
	}
	/**
	 * Detects cyclic dependency between quality variables.
	 * @throws CyclicDependencyException if there exist a cyclic dependency between quality variables.
	 */
	public void getCyclicDependentVariables() throws CyclicDependencyException{
		for (Objective obj: this.getObjectives()){
			obj.getCyclicDependentVariables(this) ;
		}
	}
	/**
	 * Visits the semantic model structure to generate the variable dependency graph.
	 * @param m semantic model obtained from parsing.
	 * @param visitor model visitor
	 */
	public void accept(ModelVisitor visitor, Model m) {
		if (m.getSubGraphObjective() == null){
			//Get the specific quality variable and call accept on that variable.
			QualityVariable var = m.getQualityVariables().get(m.getSubgraphVariable());
			// if var is null, then generate and/or- graoh for the whole model.
			if(var != null){
				var.accept(visitor, m);
			}else{
				for (Objective obj: this.getObjectives()){
					obj.accept(visitor, m);
				}
			}
			
		}else{
			m.getSubGraphObjective().accept(visitor, m);
		}
		
		visitor.visit(this);
	}
 
	/**
	 * Generates the subgraph for any model element.
	 * @param e model element to be visited, whcih could be an objective or a quality variable.
	 * @param m semantic model obtained from parsing.
	 * @param subGraphObjective subgraph objective used to restrict the genration of a goal graph to a particular objective.
	 *@return generated DOT graph for a model element.
	 */
	public String generateDOTRefinementGraph(ModelVisitorElement e, Model m, Objective subGraphObjective){
		RefinementGraphGenerator graphGenerator = new RefinementGraphGenerator(subGraphObjective);
		e.accept(graphGenerator,m);
		return graphGenerator.getDotString(m);
	}

	// 
	/**
	 * Generates the refinement graph for the whole model
	 * @param m semantic model obtained from parsing.
	 * @param subGraphObj subgraph objective used to restrict the genration of a goal graph to a particular objective.
	 *@return generated DOT graph for the model.
	 */
	public String generateDOTRefinementGraph(Model m, Objective subGraphObj){
		return generateDOTRefinementGraph(this, m,subGraphObj);
	}
	/**
	*@param d1 decision d1
	*@param d0 decision d0
	*@param option selected option
	*@param allSolutions  all generated solutions.
	* @return true if decision d1 is dependent on selection of option 'option' in decision d0; in other words, if d1 is null every time 'option' is not selected in d0.
	* Formally: isDependent(d1, d0, option) iff for all s: solution| s.selection(d0) != option imples s.selection(d1) == null
	*/
	
	boolean isDependent(Decision d1, Decision d0, String option, List<Solution> allSolutions){
		boolean result = true;
		if (d1.equals(d0)){
			return false;
		}
		for (Solution s: allSolutions){
			if(s.selection(d0) != null){
				if ( !s.selection(d0).equals(option) && s.selection(d1) != null) return false;
			}else{
				result =false;
			}
		}
		return result;
	}
	/**
	* Generates the decision diagram in DOT format.
	* @param allSolutions all generated solutions.
	* @return DOT graph of the decision model.
	*/
	public String generateDecisionDiagram(List<Solution> allSolutions){
		int idCounter =0;
		Map<Decision, Integer> decisionIDs = new LinkedHashMap<Decision, Integer>();
		List<String> edges = new ArrayList<String>();
		String result = "digraph G { \n";
		for(Decision d: this.getDecisions()){
			Integer dID = idCounter++;
			if (!decisionIDs.containsKey(d)){
				decisionIDs.put(d, dID);
			}else{
				dID = decisionIDs.get(d);
			}
			String dShape =  "\"" + dID +  "\""  + "[label=\"" + d.getDecisionLabel() +"\", shape = polygon, sides =8 ]"; 
			result +=  dShape;
			for(String option: d.getOptions()){
				Integer optionID =idCounter++;
				String optionShape = "\"" + optionID +  "\""  + "[label=\"" + option +"\"]"; 
				result +=  optionShape;
				
				String newLine = "\"" + dID + "\"" + " -> " + "\"" + optionID + "\"" + "\n";
				if (!edges.contains(newLine)){
					result = result + newLine;
					edges.add(newLine);
				}
				for (Decision d1: this.getDecisions()){
					if(this.isDependent(d1, d, option,allSolutions)){
						Integer d1ID = idCounter++;
						if (!decisionIDs.containsKey(d1)){
							decisionIDs.put(d1, d1ID);
						}else{
							d1ID = decisionIDs.get(d1);
						}
						String d1Shape =  "\"" + d1ID +  "\""  + "[label=\"" + d1.getDecisionLabel() +"\", shape = polygon, sides =8 ]"; 
						result +=  d1Shape;
						newLine = "\"" + optionID + "\"" + " -> " + "\"" + d1ID + "\"" + "\n";
						if (!edges.contains(newLine)){
							result = result + newLine;
							edges.add(newLine);
						}
					} 
				}
			}
		}
		result += "}";
		return result;
	}
	/**
	* Checks if specified objective exist in a model.
	* @param objectiveName name of the objective to check if it exist.
	* @throws CyclicDependencyException  if objective with the specified name does not exist.
	*/
	public void objectiveExist ( String objectiveName) throws Exception{
		if (objectiveName != null && !objectiveName.isEmpty()){
			List<Objective>  objs = this.getObjectives();
			boolean exist = false;
			for (int i =0; i <objs.size(); i ++ ){
				if (objs.get(i).getLabel().equals(objectiveName.trim())){
					exist =true;
				}
			}
			if (exist == false){
				throw new ModelException (""+ "Specified objective name "+ objectiveName + " is not an optimisation objective in the model."); 
			}
		}
	}
	/**
	* Adds negative sign for a maximisation objective before finding pareto optimal solutions.
	* @param evaluatedSoltions map of evaluated solutions, where the map key represent a solution and map value is the simulated objective values.
	*@return updated evaluated solutions.
	*/
	Map<Solution, double[]> addMaximisationSign (Map<Solution, double[]> evaluatedSoltions){
		Map<Solution, double[]> evaluatedSolutions = new LinkedHashMap<Solution, double[]> ();
		evaluatedSolutions.putAll(evaluatedSoltions);
		for (Map.Entry<Solution, double[]> entry: evaluatedSolutions.entrySet() ){
			for (int i =0; i < entry.getValue().length ; i++){
				if (this.getObjectives().get(i).getIsMinimisation() == false){
					entry.getValue()[i] = entry.getValue()[i] *-1;
				}
			}
		}
		return evaluatedSolutions;
	}
	void addUndefinedQualityVariable (String undefinedQualityVariable){
		this.undefinedQualityVariable.add(undefinedQualityVariable);
	}
	List<String> getUndefinedQualityVariables (){
		return undefinedQualityVariable;
	}
}
