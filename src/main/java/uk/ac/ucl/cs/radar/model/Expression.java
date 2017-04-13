package uk.ac.ucl.cs.radar.model;

import uk.ac.ucl.cs.radar.exception.CyclicDependencyException;
import uk.ac.ucl.cs.radar.exception.ParameterDistributionException;

/**
 * @author Saheed Busari and Emmanuel Letier
 */
 abstract class Expression implements ModelVisitorElement {
	 
	 QualityVariable parent;
	 /**
	 * Simulates a solution s.
	 * @param s a solution to be simulated through monte-carlo simulation.
	 * @return an array of simulated values.
	 */
	public abstract double [] simulate (Solution s);
	/**
	 * Traverses an expression recursively until reaching the leaf quality variables to get all solutions.
	 * @param m semantic model obtained from parsing.
	 * @return a set of solutions constructed while traversing a binary expression from the leaf quality variables up to the point of calling expression.
	 */
	public abstract SolutionSet getAllSolutions(Model m);
	
	/**
	 * Returns the parent of an expression.
	 * @return a quality variable that is a parent of an expression.
	 */
	public QualityVariable getParent() {
		return parent;
	}
	
	/**
	 * Sets the parent of an expression.
	 * @param parent a quality variable that is a parent of an expression.
	 */
	public void setParent(QualityVariable parent) {
		this.parent = parent;
	}
	
	/**
	 * Visits the children of an expression to generate the AND/OR variable dependency graph.
	 * @param m semantic model obtained from parsing.
	 *@param visitor model visitor
	 */
	public abstract void accept (ModelVisitor visitor, Model m);
	
	/**
	 * Traverses a quality variable expression recursively until reaching the leaf quality variables to check for cyclic dependencies between quality variables.
	 * @param m semantic model obtained from parsing.
	 * @throws CyclicDependencyException if there exist a cyclic dependency between quality variables.
	 */
	public abstract void getCyclicDependentVariables (Model m)throws CyclicDependencyException ;
	
	public abstract double getParamExpressionValue (Model m) throws ParameterDistributionException;
	
	public Expression() {}
	
}
