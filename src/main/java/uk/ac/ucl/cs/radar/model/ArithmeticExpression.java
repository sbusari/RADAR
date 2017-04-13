package uk.ac.ucl.cs.radar.model;

import java.util.List;
/**
 * @author Saheed Busari and Emmanuel Letier
 */
abstract class ArithmeticExpression extends Expression {
	public abstract double [] simulate (Solution s);
	abstract List<QualityVariable> getQualityVariable ();

}
