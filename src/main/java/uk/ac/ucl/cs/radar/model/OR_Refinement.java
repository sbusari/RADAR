package uk.ac.ucl.cs.radar.model;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.ac.ucl.cs.radar.exception.CyclicDependencyException;
import uk.ac.ucl.cs.radar.exception.ParameterDistributionException;
/**
 * @author Saheed A. Busari and Emmanuel Letier
 */
public class OR_Refinement extends Expression {
	/**
	 * The decision that correspond to the OR refinement
	 */
	private Decision decision;
	/**
	 * The AND_Refinements that correspond to the options of the OR_Refinement
	 */
	private Map<String, AND_Refinement> definition;
	/**
	 * A variable that this OR_Refinement defines.
	 */
	QualityVariable parent;
	/**
	 * Stores a boolean value to determine whether this OR_Refinement has mutually exclusive options or non-mutually exclusive options
	 */
	private boolean mutuallyExclusiveOptions;
	
	public OR_Refinement(){
		definition = new LinkedHashMap<String, AND_Refinement>();
	}
	/**
	 * Simulates a solution s.
	 * @param s a solution to be simulated through monte-carlo simulation.
	 * @return an array of simulated values.
	 */
	@Override
	public double[] simulate(Solution s) {
		String option = s.selection(decision);
		AND_Refinement and_ref = definition.get(option);
		return and_ref.simulate(s);
	}
	/**
	 * To be used for non-mutually exculusive selection
	 * @param s
	 * @return
	 */
	public double[] simulate2(Solution s) {
		// get the list of options attached to a decision (implement this in solution class)
		// initialise a 1 dim array (One[]) whose values are just 1.
		// for each option get the AND-Refinement{
		//	AND_Refinement and_ref = definition_.get(option);
		//  double [] ans = and_ref.simulate(s)
		//  One[i] = One[i] * ans[i];
		// }
		// return One[];
		return new double[]{0};
	}
	/**
	 * Sets the decision for the OR_Refinement.
	 * @param decision OR_Refinement decision.
	 */
	public void setDecision (Decision decision){
		this.decision = decision;
	}
	/**
	 * @return OR_Refinement decision.
	 */
	public Decision getDecision (){
		return decision;
	}
	/**
	 * Adds all arithemetic definitions of all alternate AND_Refinement to the OR_Refinement.
	 * @param definition a map of arithmetic expression, where the map key is the option name and the map value is the AND_Refinement corresponding to the option.
	 */
	public void setDefinition (Map<String, AND_Refinement>  definition){
		this.definition= definition;
	}
	/**
	 * @return OR_Refinement definition, which is a map of arithmetic expression, where the map key is the option name and the map value is the AND_Refinement corresponding to the option.
	 */
	public Map<String, AND_Refinement>  getDefinition (){
		return definition;
	}
	/**
	 * Adds individual arithemetic definition of all alternate options to OR_Refinement.
	 * @param option_name  option name that correspond to the AND_Refinement def.
	 * @param def  AND_Refinement to be added.
	 */
	public void addDefinition (String option_name, AND_Refinement def){
		definition.put(option_name, def);
	}
	/**
	 * @return a quality variable that is a parent of an OR_Refinement.
	 */
	@Override
	public QualityVariable getParent() {
		return parent;
	}
	/**
	 * Adds the parent of an OR_Refinement.
	 * @param parent the quality variable that is a parent of the OR_Refinement.
	 */
	public void setParent(QualityVariable parent) {
		this.parent = parent;
	}
	/**
	 * Traverses the model recursively from a OR_Refinement to its children (alternate AND_Refinement) up to the leaf quality variables of the model.
	 * @param m semantic model obtained from parsing.
	 * @return solutions from the leaf quality variables of the decision model constructed up to the point of this OR_Refinement, where solutions are combined.
	 */
	@Override
	public SolutionSet getAllSolutions(Model m){
		SolutionSet result = new SolutionSet();
		for (String option: this.decision.getOptions()){
			AND_Refinement ref = definition.get(option);
			SolutionSet solutions = ref.getAllSolutions(m);
			 if (solutions.isEmpty()){
                Solution s = new Solution();
                s = s.addDecision(this.decision, option);
                result.add(s);
	         }else {
                for(Solution s: solutions.set()){
                    s = s.addDecision(this.decision, option);
                    result.add(s);
                }
	         }
			
		}
		return result;
	}
	/**
	 * To be used for non-mutually exculusive selection
	 * @param m
	 * @return
	 */
	public SolutionSet getAllSolutions2(Model m){
		SolutionSet result = new SolutionSet();
		if (mutuallyExclusiveOptions == true){
			for (String option: decision.getOptions()){
				AND_Refinement ref = definition.get(option);
				SolutionSet solutions = ref.getAllSolutions(m);
				 if (solutions.isEmpty()){
	                Solution s = new Solution();
	                s = s.addDecision(decision, option);
	                result.add(s);
		         }else {
	                for(Solution s: solutions.set()){
	                    s = s.addDecision(decision, option);
	                    result.add(s);
	                }
		         }
			}
		}else{
			// get the size of the options
			// generate the bit array for selecting an option
			// for each row A of the 2 X 2 matrix  
			//     Initialise main solution set SS
			//     for each element in A
			//         Initialise a solution S
			//         for each option to be selected
			//			 get the AND-Ref and its solution set ss
			//           S.add(ss)
			//	   SS.add(S);
		}
		return result;
	}
	/**
	 * Visits the children of OR_Refinement to generate the variable dependency graph.
	 * @param visitor model visitor
	 * @param m semantic model obtained from parsing.
	 */
	@Override
	public void accept(ModelVisitor visitor, Model m) {
		for (AND_Refinement andRef : getAndrefinements()){
			andRef.accept(visitor,m);
		}
		visitor.visit(this);
		
	}
	/**
	 * @return all AND_Refinemnt of a OR_Refinement
	 */
	List<AND_Refinement> getAndrefinements(){
		return new ArrayList<AND_Refinement>(definition.values());
	}
	/**
	 * Traverses the model recursively from a OR_refinement to its children (AND_Refinement) and to the leaf quality variables of the model to check for cyclic dependencies between quality variables.
	 * @param m semantic model obtained from parsing.
	 * @throws CyclicDependencyException if there exist a cyclic dependency between quality variables.
	 */
	@Override
	public void getCyclicDependentVariables(Model m) throws CyclicDependencyException {
		for (AND_Refinement andRef : getAndrefinements()){
			andRef.getCyclicDependentVariables(m);
		}
	}
	@Override
	public double getParamExpressionValue(Model m) throws ParameterDistributionException {
		throw new RuntimeException ("And-Refinement variable cannot be used as an argument for another distribution.");
	}
	
}
