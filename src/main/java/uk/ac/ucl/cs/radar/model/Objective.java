package uk.ac.ucl.cs.radar.model;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.ac.ucl.cs.radar.exception.CyclicDependencyException;
/**
 * @author Saheed Busari and Emmanuel Letier
 * This class holds the objective of the model. 
 */
public  class Objective implements ModelVisitorElement {
	/**
	 * stores the precision margin to round-off numbers
	 */
	private double margin;
	/**
	 * boolean value to store whether an objective  is minimisation or maximistion
	 */
	private boolean isMinimisation;
	/**
	 * stores the name of an objective.
	 */
	private String label;
	/**
	 * the variable that is linked to an objective and defines it.
	 */
	private QualityVariable qualityVariable;
	/**
	 * A statistic definition e.g, expectation, probability or percentile
	 */
	private Statistic definition;
	/**
	 * Stores the optimisaiton value of an objective.
	 */
	private Map<Solution, Double> value;
	public Objective(){}
	/**
	 * Copy constructor.
	 * @param o objective to copy.
	 */
	public Objective (Objective o){
		isMinimisation = o.getIsMinimisation();
		label = o.getLabel();
		qualityVariable = o.getQualityVariable();
		definition =o.getStatistic();
		margin = o.getMargin();
		
	}
	/**
	 * Sets a boolean value if an objective is a minimisation objective.
	 * @param isMinimisation boolean value set if objective is a minimisation objective.
	 */
	public void setIsMinimisation(boolean isMinimisation ){
		this.isMinimisation =isMinimisation;
	}
	/**
	 * @return a boolean value if an objective is a minimisation objective.
	 */
	public boolean getIsMinimisation(){
		return isMinimisation;
	}
	/**
	 * Sets the name of the objective.
	 * @param label name of the objective.
	 */
	public void setLabel(String label ){
		this.label =label;
	}
	/**
	 * @return the name of the objective.
	 */
	public String getLabel (){
		return label;
	}
	/**
	 * Sets a margin for an objective to reduce modelling error.
	 * @param margin objective margin to set.
	 */
	public void setMargin(double margin ){
		this.margin =margin;
	}
	/**
	 * @return objective margin  value.
	 */
	public double getMargin (){
		return margin;
	}
	/**
	 * Sets the quality variable an objective refers to.
	 * @param qualityVariable the quality variable an objective refers to.
	 */
	public void setQualityVariable(QualityVariable qualityVariable ){
		this.qualityVariable =qualityVariable;
	}
	/**
	 * @return the quality variable an objective refers to.
	 */
	public QualityVariable getQualityVariable (){
		return qualityVariable;
	}
	/**
	 * Sets the statistic definition of an objective.
	 * @param definition the statistic definition of an objective.
	 */
	public void setStatistic (Statistic definition){
		this.definition =  definition;
	}
	/**
	 * @return the statistic definition of an objective.
	 */
	public Statistic getStatistic (){
		return this.definition;
	}
	/**
	 * Evaluates the objective values for a solution s.
	 * @param s the solution to simulate through monte-carlo simulation.
	 * @return the simulated values of an objective.
	 */
	public double evaluate (Solution s){
		if (value == null){
			value = new LinkedHashMap<Solution, Double>();
			double result  = definition.evaluate(s, qualityVariable);
			value.put(s, result);
			return result;
		}
		else if (value.get(s) == null) {
			double result  = definition.evaluate(s, qualityVariable);
			value.put(s, result);
			return result;
		}
		else{
			return value.get(s);
		}
	}
	/**
	 * @param m semantic model obtained through parsing.
	 * @return all solutions for an objective instance.
	 */
	public SolutionSet getAllSolutions(Model m){
		QualityVariable var = this.getQualityVariable();
		return var.getAllSolutions(m);
	}
	/**
	 * Traverses the model recursively from a quality variable objective refers to and to the leaf quality variables of the model to check for cyclic dependencies between quality variables.
	 * @param m semantic model obtained from parsing.
	 * @throws CyclicDependencyException if there exist a cyclic dependency between quality variables.
	 */
	public void getCyclicDependentVariables(Model m) throws CyclicDependencyException{
		QualityVariable var = this.getQualityVariable();
		var.getCyclicDependentVariables(m);
	}
	/**
	 * Visits the quality variable an objective refers to and generate the variable dependency graph.
	 *@param visitor model visitor
	 * @param m semantic model obtained from parsing.
	 */
	@Override
	public void accept(ModelVisitor visitor, Model m) {
		definition.accept(visitor, m);
		visitor.visit(this);
	}
	@Override
	public int hashCode (){
		if (qualityVariable == null){
			return 0;
		}
		return qualityVariable.hashCode();
	}

}
