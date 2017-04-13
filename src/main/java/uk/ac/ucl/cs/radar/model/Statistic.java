package uk.ac.ucl.cs.radar.model;
 abstract class Statistic implements ModelVisitorElement {
	ArithmeticExpression expression;
	/**
	 * @return definition an arithmetic expression that defines a statistic.
	 */
	public ArithmeticExpression getObjExpression(){
		return expression;
	}
	/**
	 * Sets the arithemetic definition of a statistic.
	 * @param expression an arithmetic expression that defines a statistic.
	 */
	public void setObjExpression(ArithmeticExpression expression){
		this.expression = expression;
	}
	/**
	 * Visits the objective statistic's definition to generate the AND/OR variable dependency graph.
	 * @param m semantic model obtained from parsing.
	 *@param visitor model visitor
	 */
	public void accept(ModelVisitor visitor, Model m) {
		expression.accept(visitor,m);
		visitor.visit(this);
	}
	abstract double evaluate (Solution s, QualityVariable var);
}
