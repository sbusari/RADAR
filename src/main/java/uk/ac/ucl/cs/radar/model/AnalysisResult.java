package uk.ac.ucl.cs.radar.model;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import uk.ac.ucl.cs.radar.utilities.TableBuilder;

/**
 * @author Saheed Busari and Emmanuel Letier
 * This class holds the results of model analysis.
 */
public class AnalysisResult {
	
	/**
	 * the model optimisation objectives.
	 */
	private List<Objective> objectives;

	/**
	 *  the list of model decisions
	 */
	private List<Decision> decisions;
	
	/**
	 * value.get(s)[j] gives the value of solution s for the j^th objective
	 */
	private Map<Solution, double[]> value; 
	
	/**
	 *  the list of shortlisted solutions
	 */
	private Map<Solution, double[]> shortlist;	
	
	/**
	 *  the list of all generated solutions
	 */
	private List<Solution> allSolutions;
	
	/**
	 *  objective with respect to which information value is measured
	 */
	private Objective eviObjective;
	
	/**
	 * objective to which the generated goal-graph starts from
	 */
	private Objective subGraphObjective;

	/**
	 * computed value of evtpi
	 */
	private double evtpi;
	
	/**
	 *  the total number of possible solutions in the model
	 */
	private long solutionSpace;
	
	/**
	 *  total runtime used to analyse a model
	 */
	private long totalRuntime;
	
	/**
	 *  total number of valid solutions in a decision model
	 */
	private long designSpaceRuntime;
	
	/**
	 *  total time used to perform MonteCarlo simulation. 
	 */
	private long simulationRuntime;
	
	/**
	 * total time used to shortlist Pareto optimal solutions.
	 */
	private long optimisationRuntime;
	
	/**
	 * total time used to compute the evtpi and evppi
	 */
	private long informationValueRuntime;
	/**
	 * the number of decimal precision to round-off results
	 */
	private int decimalPrecision;
	
	/**
	 * the number of model variables.
	 */
	private int nbrVariables;
	
	/**
	 * the number of decisions in the model.
	 */
	private int nbrOfDecision;
	
	/**
	 * the number of model parameter estimations.
	 */
	private int nbrParameters;
	
	/**
	 * the messgae displayed on the console of the GUI
	 */
	private String consoleMessage = "";

	/**
	 *  mapping from parameter's label to parameter's evppi
	 */
	private Map<String, Double> evppi;
	/**
	 * Constructors analysis result with model objectives and model decisions.
	 * @param objs model objectives.
	 * @param ds model decisons.
	 */
	AnalysisResult(List<Objective> objs,  List<Decision> ds){
		objectives = objs;
		decisions = ds;
		value = new LinkedHashMap<Solution, double[]>();
		evppi = new LinkedHashMap<String, Double>();
	}
	
	/**
	 * Adds a solution and it evaluated objectives to a list of evaluated solutions.
	 * @param s a solution to be added.
	 * @param objValues computed objective values. 
	 */
	public void addEvaluation (Solution s, double [] objValues){
		value.put(s, objValues);
	}
	/**
	 * Returns all evaluated solutions and their corresponding objective values.
	 * @return evaluated solutions
	 */
	public Map<Solution, double[]> getEvaluatedSolutions (){
		return value;
	}
	/**
	 *Returns all generated solutions.
	 *@return minimal set of generated solutions
	 */
	public List<Solution> getAllSolutions(){
		return allSolutions;
	}
	/**
	 * Adds a shortlisted solution to the list of shorlists.
	 * @param optimalSolns an optimal solution to be added.
	 */
	public void addShortlist (Map<Solution, double[]> optimalSolns){
		shortlist= optimalSolns;
	}
	/**
	 * Returns all shortlisted solutions.
	 * @return shortlisted solutions
	 */
	public List<Solution> getShortListSolutions (){
		return new ArrayList<Solution>(shortlist.keySet());
	}
	/**
	 * Returns all shortlisted solutions objectives.
	 * @return shortlisted objective values.
	 */
	public List<double[]> getShortListObjectives (){
		return new ArrayList<double[]>(shortlist.values());
	}
	/**
	 * Returns all evaluated solutions objectives.
	 * @return   evaluated objectives
	 */
	public List<double[]> getEvaluatedObjectives (){
		return new ArrayList<double[]>(value.values());
	}
	/**
	 * Adds the computed evtpi value to analysis result.
	 * @param evtpi_value computed evtpi
	 */
	public void addEVTPI (double evtpi_value){
		evtpi = evtpi_value;
	}
	/**
	 * Adds all generated solutions to analysis result.
	 * @param allSolns all generated solutions
	 */
	void addAllSolutions (List<Solution> allSolns){
		allSolutions = allSolns;
	}
	/**
	 * Adds a model parameter and its evppi value to analysis result.
	 * @param param model parameter for which we intend to reduce uncertainty.
	 * @param n computed evppi. 
	 */
	void addEVPPI(String param, Double n){
		evppi.put(param, n);
	}
	/**
	 * Adds specified information value objective to analysis result.
	 * @param eviObj expected value information objective
	 */
	void addEviObjective (Objective eviObj){
		eviObjective = eviObj;
	}
	/**
	 * Adds the model solution space to analysis result.
	 * @param solnSpace solution space
	 */
	void addSolutionSpace (long solnSpace){
		solutionSpace = solnSpace;
	}
	public long getSolutionSpace (){
		return solutionSpace;
	}
	/**
	 * Adds the model analysis runtime to analysis result.
	 * @param time execution analysis run time.
	 */
	/**
	 * @param time
	 */
	void addRunTime (long time){
		totalRuntime = time;
	}
	/**
	 * @return analysis run time.
	 */
	public long getRunTime (){
		return totalRuntime;
	}
	
	/**
	 * @param design space time
	 */
	void addDesignSpaceRunTime (long designSpaceTime){
		designSpaceRuntime = designSpaceTime;
	}
	/**
	 * @return design space run time.
	 */
	public long getDesignSpaceRunTime (){
		return designSpaceRuntime;
	}
	/**
	 * @param simulation run time
	 */
	void addSimulationRuntime (long simulationTime){
		simulationRuntime = simulationTime;
	}
	/**
	 * @return simulation run time.
	 */
	public long getSimulationRuntime (){
		return simulationRuntime;
	}
	/**
	 * @param optimisation run time
	 */
	void addOptimisationRuntime (long optimisationTime){
		optimisationRuntime = optimisationTime;
	}
	/**
	 * @return optimisation run time.
	 */
	public long getOptimisationRuntime (){
		return optimisationRuntime;
	}
	/**
	 * @param informationValue run time
	 */
	void addInformationValueRuntime (long informationValueTime){
		informationValueRuntime = informationValueTime;
	}
	/**
	 * @return informationValue run time.
	 */
	public long getInformationValueRuntime (){
		return informationValueRuntime;
	}
	/**
	 * @param Decimal precision
	 */
	 public void addDecimalPrecision (int decimalPrecisionValue){
		decimalPrecision = decimalPrecisionValue;
	}
	/**
	 * Adds the number of quality variables to analysis result.
	 * @param nbrVar execution analysis run time.
	 */
	void addNumberOfVariables (int nbrVar){
		nbrVariables = nbrVar;
	}
	/**
	 * @return the number of quality variables.
	 */
	public int getNumberOfVariables (){
		return nbrVariables;
	}
	/**
	 * Adds the number of model parameters to analysis result.
	 * @param nbrParam execution analysis run time.
	 */
	void addNumberOfParameters (int nbrParam){
		nbrParameters = nbrParam;
	}
	/**
	 * @return the number of quality variables.
	 */
	public int getNumberOfParameters (){
		return nbrParameters;
	}
	/**
	 * Adds the number of decisions to analysis result.
	 * @param nbrDecision the number of decisions.
	 */
	void addNumberOfDecisions (int nbrDecision){
		nbrOfDecision = nbrDecision;
	}
	/**
	 * @return the number of quality variables.
	 */
	public int getNumberOfDecisions (){
		return nbrOfDecision;
	}
	/**
	 * Adds specified subgraph objective to analysis result.
	 * @param subGraphObj subgraph objective that restrict the variable dependency graph to a particular objective.
	
	 */
	void addSubGraphObejctive (Objective subGraphObj){
		subGraphObjective =subGraphObj;
	}
	/**
	 * Returns all model objective.
	 * @return model obejectives.
	 */
	public List<Objective> getObjectives (){
		return objectives;
	}
	/**
	 * Returns subgraph objective that restrict the variable dependency graph to a particular objective.
	 * @return subgraph obejective.
	 */
	public Objective getSubGraphObjective (){
		return subGraphObjective;
	}
	public void setConsoleMessage (String progressMessage){
		consoleMessage = progressMessage;
	}
	public String getConsoleMessage (){
		return consoleMessage;
	}
	
	public String generateSolutionHeader ( String separator){
		// we can't use decision to generate the header due to change in the getAllSolutions
		String result ="ID" + separator;
		for(int i =0 ; i  < decisions.size(); i ++){
			result += decisions.get(i).getDecisionLabel() + separator;
		}
		for(int j=0; j < objectives.size(); j++){
			result +=objectives.get(j).getLabel() + separator;
		}
		result += "Optimal";
		return result;
	}
	public void generateSolutionHeader (TableBuilder tableBuilder){
		String result ="ID" + ",";
		for(int i =0 ; i  < decisions.size(); i ++){
			result += decisions.get(i).getDecisionLabel() + ",";
		}
		for(int j=0; j < objectives.size(); j++){
			result +=objectives.get(j).getLabel() + ",";
		}
		result += "Optimal\n";
		tableBuilder.addRow(result.split(","));
	}
	/**
	 * Returns optimisation analysis result needed for out put display.
	 * @return optimisation analysis result in string form.
	 */
	String getOptimisationAnalysisResult (){
		totalRuntime = designSpaceRuntime+simulationRuntime+ optimisationRuntime+informationValueRuntime;
		TableBuilder tableBuilder = new TableBuilder ();
		tableBuilder.addRow (new String []{"Optimisation", "Analysis"});
		tableBuilder.addRow ("-----------------------------------------", "");
		for(int j=0; j < objectives.size(); j++){
			String optimisationDirection = objectives.get(j).getIsMinimisation() ? "Min" : "Max";
			tableBuilder.addRow (new String []{"Objective: ",optimisationDirection + objectives.get(j).getLabel() +"" });
		}
		tableBuilder.addRow ("SolutionSpace:", solutionSpace +"");
		//tableBuilder.addRow ("Minimal SolutionSet:", value.size() +"\n");
		tableBuilder.addRow ("Design Sapce:", allSolutions.size() +"");
		tableBuilder.addRow ("Shortlisted:", shortlist.size() +"");
		tableBuilder.addRow ("Nbr. Variables:", nbrVariables + "");
		tableBuilder.addRow ("Nbr. Parameters:", nbrParameters + "");
		tableBuilder.addRow ("Nbr. Decisions:", nbrOfDecision + "");
		tableBuilder.addRow ("Total Runtime(s):", totalRuntime +"");
		return tableBuilder.toString();
	}
	public List<String> optimisationAnalysisDetails (){
		totalRuntime = designSpaceRuntime+simulationRuntime+ optimisationRuntime+informationValueRuntime;
		List<String> result = new ArrayList<String> ();
		for(int j=0; j < objectives.size(); j++){
			String optimisationDirection = objectives.get(j).getIsMinimisation() ? "Min" : "Max";
			result.add ("Objective ,"+optimisationDirection + objectives.get(j).getLabel());
		}
		result.add ("SolutionSpace ," + solutionSpace);
		//result.add ("Minimal SolutionSet ,"+ value.size());
		result.add ("Design Space ,"+ allSolutions.size());
		result.add ("Shortlisted ,"+ shortlist.size() );
		result.add ("Nbr. Variables ," + nbrVariables  );
		result.add ("Nbr. Parameters ," + nbrParameters  );
		result.add ("Nbr. Decisions ," + nbrOfDecision  );
		result.add ("Total Runtime(s) ,"+ totalRuntime);
		return result;
	}
	String roundOffToDesiredPrecision(double val)
	{
	    return String.format("%."+ decimalPrecision+ "f", val);
	}
	public List<String> infoValueDetails (){
		List<String> result = new ArrayList<String> ();
		if (eviObjective != null){
			result.add ("Objective,"+ eviObjective.getLabel());
			result.add("EVTPI," + evtpi + "\n" );
			result.add ("Parameter,"+ "EVPPI");
			for (Map.Entry<String, Double> entry: evppi.entrySet()){
				result.add(entry.getKey()+ ","+roundOffToDesiredPrecision(entry.getValue()) );
			}
		}
		return result;
	}
	/**
	 * Returns formatted analysis results. Includes optimisation analysis result, pareto optimal solutions and information value results.
	 *@return analysis results converted to String.
	 */
	public String analysisToString (){
		StringBuilder analysisResult = new StringBuilder();
		analysisResult.append (getOptimisationAnalysisResult());
		TableBuilder resultBuilder = new TableBuilder ();
		generateSolutionHeader(resultBuilder);
		getFormattedSolutions(0, shortlist, true,resultBuilder);
		Map<Solution, double []> nonOptimalSolutions =getNonOptimalSolutions();
		getFormattedSolutions(shortlist.size(), nonOptimalSolutions, false,resultBuilder);
		analysisResult.append (resultBuilder.toString());
		analysisResult.append ("\n");
		if (eviObjective != null){
			String infoAnalysis =informationValueResultToString();
			analysisResult.append (infoAnalysis);
		}
		//System.out.println ("Analysis result: \n"+analysisResult.toString());
		return analysisResult.toString();
	}
	public String analysisRuntimeAndMemoryToCSV (){
		StringBuilder resultToPrint = new StringBuilder ();
		resultToPrint.append("Runtime Results, , , \n");
		
		return resultToPrint.toString();
	}
	/**
	 * Returns analysis results in csv format. Includes optimisation analysis result, pareto optimal solutions and information value results.
	 *@return analysis results converted to String.
	 */
	public String analysisResultToCSV (){
		StringBuilder resultToPrint = new StringBuilder ();
		resultToPrint.append("Optimisation Analysis, \n");
		List<String> optimisationDetail= optimisationAnalysisDetails();
		for (String optDetail :optimisationDetail ){
			resultToPrint.append(optDetail + "\n");
		}
		resultToPrint.append("\n");
		resultToPrint.append(generateSolutionHeader(",") + "\n");
		List<String> solutions= solutionTable();
		for (String sol :solutions ){
			resultToPrint.append(sol);
		}
		resultToPrint.append("\n");
		resultToPrint.append ("Information Value Analysis, \n");
		List<String> infoValueDetail= infoValueDetails();
		for (String infoVal :infoValueDetail ){
			resultToPrint.append(infoVal + "\n");
		}
		return resultToPrint.toString();
	}
	String getSolutionTableRow (int index, int offset, Map<Solution, double[]> solutions, boolean isOptimal ){
		String row = "";
		row += (index+ offset+ 1 ) + "," ;
        String record = "";
        record += optimalDecisionsToString (index, ",", solutions);
        record += optimalObjectiveToString (index, ",", solutions);
        row += (record);
        String optimal = isOptimal == true ? "Yes" :"No";
        row += (optimal + "\n");
        return row;
	}
	public String getSolutionTableColumnIdentifier (){
		String shortlistHeader = generateSolutionHeader(",");
		return shortlistHeader;
	}
	public List<String> solutionTable (){
		List<String> result = new ArrayList<String>();
		for (int i =0; i < shortlist.size(); i++){
			result.add(getSolutionTableRow(i, 0, shortlist, true));
		}
		Map<Solution, double []> nonOptimalSolutions =getNonOptimalSolutions();
		for (int i =0; i < nonOptimalSolutions.size(); i++){
			result.add(getSolutionTableRow(i, shortlist.size(), nonOptimalSolutions, false));
		}
		return result;
		
	}
	private void getFormattedSolutions(int offset, Map<Solution, double[]> solutions, boolean isOptimal ,TableBuilder table){
		if (solutions.size() > 0){
			for (int index = 0; index < solutions.size(); index++) {
				String row = "";
				row += (index+ 1 + offset) + "," ;
		        String record = "";
		        record += optimalDecisionsToString (index, ",", solutions);
		        record += optimalObjectiveToString (index, ",", solutions);
		        
		        row += (record);
		        String optimal = isOptimal == true ? "Yes" :"No";
		        row += (optimal + "\n");
		        table.addRow(row.split(","));
		    }
		}
	}
	/**
	 * Returns non-Pareto optimal solutions i.e all dominated solutions.
	 */
	private Map<Solution, double []> getNonOptimalSolutions (){
		List<Integer> solutionIndexToRemove = new ArrayList<Integer>();
		int  i=0;
		for (Map.Entry<Solution, double []> allSolutionEntry : value.entrySet()){
			for (Map.Entry<Solution, double []> optimalSolutionEntry:shortlist.entrySet() ){
				if (allSolutionEntry.getKey().selectionToString().equals(optimalSolutionEntry.getKey().selectionToString())){
					solutionIndexToRemove.add(i);
					break;
				}
			}
			i++;
		}
		Map<Solution, double[]> nonOptimalSolutions = new LinkedHashMap<Solution, double[]>();
		int  k=0;
		for (Map.Entry<Solution, double []> allSolutionEntry : value.entrySet()){
			if( !solutionIndexToRemove.contains((Integer)k) ){
				 nonOptimalSolutions.put(allSolutionEntry.getKey(), allSolutionEntry.getValue());
			 }
			k++;
		}
		return nonOptimalSolutions;
	}
	private String optimalObjectiveToString (int index, String separator, Map<Solution, double[]> solutions){
		String record ="";
		List<double []> objValues = new ArrayList<double []>(solutions.values());
		int i =0;
		for(double  entry: objValues.get(index)){
			double value = objectives.get(i).getIsMinimisation() == true? entry: entry *-1;
        	record += roundOffToDesiredPrecision(value) + separator;
        	i++;
        }
		return record;
	}
	private String optimalDecisionsToString (int index,String separator, Map<Solution, double[]> solutions){
		String record ="";
		List<Solution> solutionList = new ArrayList<Solution>(solutions.keySet());
		Map<Decision, String>  selection = solutionList.get(index).getSelection();
		// we now use deicision to get the option because we do not know the order in which the selections are in solution.
		for(int i =0 ; i  < decisions.size(); i ++){
			if (!selection.containsKey(decisions.get(i))){
				record += " " + separator;
			}else{
				String decision = decisions.get(i).getDecisionLabel();
				for (Map.Entry<Decision, String> entry: selection.entrySet()){
					if (decision.equals(entry.getKey().getDecisionLabel())){
						record += entry.getValue() + separator;
						break;
					}
				}
			}
			
		}
		return record;
	}
	/**
	 * @return the formatted information value results to string.
	 */
	public  String informationValueResultToString(){
		TableBuilder result = new TableBuilder();
		result.addRow ("Information Value Analysis", "\n");
		result.addRow ("--------------------------", "\n\n");
		result.addRow ("Objective:", eviObjective.getLabel() + "\n");
		result.addRow ("EVTPI:", evtpi + "\n\n");
		result.addRow ("Parameter", "EVPPI \n");
		result.addRow ("----------","----- \n\n");
		for (Map.Entry<String, Double> entry: evppi.entrySet()){
			result.addRow(new String[]{ entry.getKey() ,  roundOffToDesiredPrecision(entry.getValue()) +"\n"});
		}
		return result.toString();
	}
	/**
	 * @return the formatted model decisions to CSV.
	 */
	public String decisionsToCSV ( ){
		StringBuilder decision = new StringBuilder ();
		int count =1;
		decision.append("ID , Decision , Options\n");
		for (int i =0; i <  decisions.size(); i ++){
			StringBuilder row = new StringBuilder();
			row.append(count + ",");
			row.append(decisions.get(i).getDecisionLabel() + ",");
			String options ="";
			options ="[";
			List<String> decisionOptions = decisions.get(i).getOptions();
			for (String option : decisionOptions){
				options += option + ", ";
			}
			options = options.trim(); 
			options = options.substring(0, options.length()-1);
			options +="]";
			row.append(options + "\n");
			decision.append(row.toString());
			count++;
		}
		return decision.toString();
	}
	public  String getReferenceObjectives () {
		String record = "";
		for (int index = 0; index < shortlist.size(); index++) {
			record += optimalObjectiveToString (index, " ", shortlist);
			record += "\n";
		}
		return record;
	}
	public String getReferenceDecisions (){
		String record = "";
		for (int index = 0; index < shortlist.size(); index++) {
			record += optimalDecisionsToString (index, " ",  shortlist);
			record += "\n";
		}
		return record;
	}
	

	
}
