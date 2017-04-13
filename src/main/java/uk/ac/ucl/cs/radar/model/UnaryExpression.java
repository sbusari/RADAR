package uk.ac.ucl.cs.radar.model;
import java.util.List;

import uk.ac.ucl.cs.radar.exception.CyclicDependencyException;

/**
 * @author Saheed Busari and Emmanuel Letier
 */
class UnaryExpression extends ArithmeticExpression {

	private UnaryOperator uop;
	private ArithmeticExpression expr;
	/**
	 * Sets operator for the unary expression.
	 * @param uop unary operator.
	 */
	public void setUnaryOperator (UnaryOperator uop){
		this.uop = uop;
	}
	/**
	 * @return a unary operator such as +, -, ! etc.
	 */
	public UnaryOperator getUnaryOperator (){
		return uop;
	}
	/**
	 * Sets the unary expression.
	 * @param expr arithmetic expression which could also be a number.
	 */
	public void setExpression (ArithmeticExpression expr){
		this.expr = expr;
	}
	/**
	 * @return the arithmetic unary expression, which could also be a number.
	 */
	public ArithmeticExpression getExpression (){
		return this.expr;
	}
	/**
	 * Simulates a solution s.
	 * @param s a solution to be simulated through monte-carlo simulation.
	 * @return an array of simulated values.
	 */
	@Override
	public double[] simulate(Solution s) {
		double [] expr = this.expr.simulate(s);
		if (uop != null){
			for (int i =0 ; i < expr.length; i ++){
				switch (uop.getUnaryOperatorValue()){
					case "+" : {
						expr[i] = expr[i] ;
					}; break;
					case "-" : {
						expr[i] =-1* expr[i];
					}; break;
					case "!" : {
						expr[i] =1 - expr[i];
					}; break;
					default : expr[i] = expr[i];
				}
			}
		}
		return expr;
	}
	/**
	 * @return a list of quality variables that defines a unary expression.
	 */
	@Override
	List<QualityVariable> getQualityVariable() {
		return expr.getQualityVariable();
	}
	/**
	 * @return a quality variable that is a parent of a unary expression.
	 */
	@Override
	public QualityVariable getParent() {
		return parent;
	}
	/**
	 * Visits the children of a unary expression to generate the AND/OR variable dependency graph.
	 * @param m semantic model obtained from parsing.
	 *@param visitor model visitor
	 */
	@Override
	public void accept(ModelVisitor visitor, Model m) {
		expr.accept(visitor, m);
		
	}
	/**
	 * Traverses the unary expression recursively until reaching the leaf quality variables.
	 * @param m parsed decison model.
	 * @return a set of solutions constructed while traversing a unary expression from the leaf quality variables to the point of the calling unary expression.
	 */
	@Override
	public SolutionSet getAllSolutions(Model m) {
		SolutionSet results = new SolutionSet();
		SolutionSet solutions= expr.getAllSolutions(m);
		results = results.merge(solutions);
		return results;
	}
	/**
	 * Traverses the model recursively from a quality variable unary expression to its children and to the leaf quality variables of the model to check for cyclic dependencies between quality variables.
	 * @param m semantic model obtained from parsing.
	 * @throws CyclicDependencyException if there exist a cyclic dependency between quality variables.
	 */
	@Override
	public void getCyclicDependentVariables(Model m) throws CyclicDependencyException {
		expr.getCyclicDependentVariables(m);
	}
	@Override
	public double getParamExpressionValue(Model m) {
		// TODO Auto-generated method stub
		return 0;
	}





}
