package uk.ac.ucl.cs.radar.model;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import uk.ac.ucl.cs.radar.exception.CyclicDependencyException;
import uk.ac.ucl.cs.radar.exception.ParameterDistributionException;

public class QualityVariable extends ArithmeticExpression implements ModelVisitorElement {

	/**
	 * Stores the name of the variable
	 */
	private String label;
	/**
	 * Represents the arithmetic expression that defines a variable.
	 */
	private Expression definition;
	/**
	 * Stores the model simulation data
	 */
	private Map<Solution, double[]> simData;
	/**
	 * Stores the list of variable names that define this quality variable
	 */
	private List<String> children;
	
	/**
	 * Constructor
	 */
	public QualityVariable(){
		simData = new LinkedHashMap<Solution, double[]>();
	}
	/**
	 * Sets the name of a quality variable in an expression.
	 * @param label quality variable name.
	 */
	public void setLabel (String label){
		this.label = label;
	}
	/**
	 * @return the name of the quality variable.
	 */
	public String getLabel (){
		return this.label;
	}
	/**
	 * Adds  definition of a quality variable instance.
	 * @param def an expression that defines a quality variable instance.
	 */
	public void setDefinition (Expression def){
		definition = def;
	}
	/**
	 * @return the expression that defines a quality variable instance.
	 */
	public Expression getDefinition (){
		return definition;
	}
	/**
	 * Sets the children names of a quality variable instance.
	 * @param children names of the quality variable children.
	 */
	public void setChildren (List<String> children){
		this.children =children;
	}
	/**
	 * @return children names of the quality variable children.
	 */
	public List<String> getChildren (){
		return this.children;
	}
	 /**
	 * Simulates a solution s for objective value computation.
	 * @param s a solution to be simulated through monte-carlo simulation.
	 * @return an array of simulated values.
	 */
	public double[] getSimData(Solution s) {
		return simulate(s);
	}
	 /**
	 * Simulate a list of solutions for objective value computation.
	 * @param s is a list of solutions to be simulated through monte-carlo simulation.
	 * @return an array of simulated values.
	 */
	public double[][] getSimData(List<Solution> s) {
		double [][] result = new double [s.size()][];
		for (int i =0 ; i < s.size(); i ++){
			result[i] = getSimData (s.get(i));
		}
		return result;
	}
	 /**
	 * Simulate a list of solutions.
	 * @param s is a list of solutions to be simulated through monte-carlo simulation.
	 * @return an array of simulated values.
	 */
	double[][] simulate(List<Solution> s) {
		double [][] result = new double [s.size()][];
		for (int i =0 ; i < s.size(); i ++){
			result[i] = simulate(s.get(i));
		}
		return result;
	}
	 /**
	 * Simulates a solution s for objective value computation.
	 * @param s a solution to be simulated through monte-carlo simulation.
	 * @return an array of simulated values.
	 */
	public double [] simulate (Solution s){
		return definition.simulate(s);
	}
	/**
	 * @return a mapping between a set of simulated solutions and simulated objective values..
	 */
	public Map<Solution, double[]> getSimData(){
		return simData;
	}
	/**
	 * Sets the simulation data for a quality variable instance.
	 * @param simdata a mapping between a set of simulated solutions and simulated objective values.
	 */
	public void setSimData(Map<Solution, double[]> simdata){
		simData =simdata;
	}
	/**
	 * @return a list of quality variables that is part of an expression definition. i.e. the current quality varible instance.
	 */
	@Override
	List<QualityVariable> getQualityVariable() {
		List<QualityVariable> result = new ArrayList<QualityVariable>();
		result.add(this);
		return result;
	}
	
	public int hashCode(){
		return this.getLabel().hashCode();
	}
	/**
	 * Traverses the model recursively from a quality variable to its children and to the leaf quality variables of the model.
	 * @param m semantic model obtained from parsing.
	 * @return solutions constructed recursively from the leaf quality variables of the decision model up to the point of the calling quality variable instance.
	 */
	@Override
	public SolutionSet getAllSolutions(Model m){
		Expression expr = this.definition;
		return expr.getAllSolutions(m);
		
	}
	/**
	 *Visits the children of a quality variable instance to generate the AND/OR variable dependency graph.
	 *@param m semantic model obtained from parsing.
	 *@param visitor model visitor
	 */
	@Override
	public void accept(ModelVisitor visitor, Model m) {
		// definition for the option may be null, if it was populated partially during parsing for an arithmtetic expr 
		if (definition == null){ 
			QualityVariable qv = m.getQualityVariables().get(label);
			// if we consider parameter, the label would have smt like qv_name[option], hence, qv may be null as, such qv name do not exist.
			if (qv != null){
				qv.getDefinition().accept(visitor,m);
				// update the definition of the this quality varible, so that when it visit itself it can prints the children of the arithmetic expression if it they exist.
				this.setDefinition(qv.getDefinition());
			}
		}else{
			definition.accept(visitor,m);
		}
		visitor.visit(this);
	}
	/**
	 * @return a quality variable that is a parent of a quality variable instance.
	 */
	@Override
	public QualityVariable getParent() {
		return null;
	}
	/**
	 * Traverses the model recursively from a quallity variable  to its children and to the leaf quality variables of the model to check for cyclic dependencies between quality variables.
	 * @param m semantic model obtained from parsing.
	 * @throws CyclicDependencyException if there exist a cyclic dependency between quality variables.
	 */
	@Override
	public void getCyclicDependentVariables(Model m) throws CyclicDependencyException {
		definition.getCyclicDependentVariables(m);
	}
	@Override
	public double getParamExpressionValue(Model m) throws ParameterDistributionException {
		return definition.getParamExpressionValue(m);
		
	}





	
}
