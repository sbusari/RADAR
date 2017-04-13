package uk.ac.ucl.cs.radar.model;

import java.util.ArrayList;
import java.util.List;
;
/**
 * @author Saheed Busari and Emmanuel Letier
 */
class Number extends ArithmeticExpression {

	private double value;
	public void setValue (double value){
		this.value = value;
	}
	public double  getValue (){
		return value;
	}
	
	@Override
	public double[] simulate(Solution s) {
		// Expr in the argument of a deterministic distribution have been handled that during parsing
		// Here, we handle a binary expression that has a number as an operand.
		int simulationNo = s.getSemanticModel().getNbr_Simulation();
		double [] sim = new double [simulationNo];
		// replicating number for the number of simulation to facilitate arithmetic computation of an expr
		for (int i = 0; i < sim.length; ++i) {
			sim[i] = value;
		}
		return sim;
	}
	@Override
	List<QualityVariable> getQualityVariable() {
		return new ArrayList<QualityVariable>();
	}
	@Override
	public QualityVariable getParent() {
		return null;
	}
	@Override
	public void accept(ModelVisitor visitor, Model m) {

	}
	@Override
	public SolutionSet getAllSolutions(Model m) {
		return new SolutionSet();
	}
	@Override
	public void getCyclicDependentVariables(Model m) {
	
	}
	@Override
	public double getParamExpressionValue(Model m) {
		return value;
	}
}
