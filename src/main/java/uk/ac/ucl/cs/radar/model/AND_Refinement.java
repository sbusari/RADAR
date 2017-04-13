package uk.ac.ucl.cs.radar.model;
import java.util.List;

import uk.ac.ucl.cs.radar.exception.CyclicDependencyException;
/**
 * @author Saheed Busari and Emmanuel Letier
 */
class AND_Refinement extends Expression {

	/**
	 * the decision name in which this ANDRef refers to 
	 */
	String aDecisionAndRefRefersTo;
	/**
	 * The variable that this ANDRef defines
	 */
	QualityVariable parent;
	/**
	 * The arithmetic expression that defines this ANDRef
	 */
	private ArithmeticExpression definition;
	/**
	 * Simulates a solution s.
	 * @param s a solution to be simulated through monte-carlo simulation.
	 * @return an array of simulated values.
	 */
	@Override
	public double[] simulate(Solution s) {
		return this.definition.simulate(s);
	}
	/**
	 * Returns a list of quality variables that are AND_Refienement children.
	 * @return a list of quality variables.
	 */
	List<QualityVariable> getChildren(){
		return this.definition.getQualityVariable();
	}
	/**
	 * Adds arithemetic definition of the AND_Refinement.
	 * @param definition an arithmetic expression that defines the AND_Refinement.
	 */
	public void addDefinition (ArithmeticExpression definition){
		this.definition = definition;
	}
	/**
	 * @return the arithemetic definition of the AND_Refinement.
	 */
	public ArithmeticExpression getDefinition (){
		return this.definition;
	}
	/**
	 * @return a quality variable that is a parent of an AND_Refinement.
	 */
	@Override
	public QualityVariable getParent() {
		return parent;
	}
	/**
	 * Adds the parent of an AND_Refinement.
	 * @param parent the quality variable that is a parent of the AND_Refinement.
	 */
	public void setParent(QualityVariable parent) {
		this.parent = parent;
	}
	/**
	 * Sets decision name AND_Refinement refers to during parsing to be used in generating variable dependency graph.
	 * @param decisionNameAndRefReferTo decision name AND_Refinement refers to
	 */
	public void setDecisionNameAndRefRefersTo (String decisionNameAndRefReferTo){
		this.aDecisionAndRefRefersTo = decisionNameAndRefReferTo;
	}
	/**
	 * @return decision name AND_Refinement refers to
	 */
	public String getDecisionNameAndRefRefersTo (){
		return this.aDecisionAndRefRefersTo;
	}
	/**
	 * Traverses the model recursively from a AND_refinement to its children and to the leaf quality variables of the model.
	 * @param m semantic model obtained from parsing.
	 * @return solutions constructed recursively from the leaf quality variables of the decision model up to the point of the calling AND_Refinement, where solutions are merged.
	 */
	public SolutionSet getAllSolutions(Model m){
		SolutionSet result = new SolutionSet();
		for (QualityVariable var: this.getChildren()){
			if (var.getDefinition() == null){ 
				QualityVariable qv = m.getQualityVariables().get(var.getLabel());
				if (qv != null){
					result = result.merge(qv.getAllSolutions(m));
				}else{ // if it is a paramter within an expr it  will return null cos its labe does not exist
					Solution s = new Solution();
					result.add(s);
				}
			}else{
				result = result.merge(var.getAllSolutions(m));
			}
		}
		return result;
	}
	/**
	 * Visits the children of AND_Refinement to generate the AND/OR variable dependency graph.
	 * @param m semantic model obtained from parsing.
	 *@param visitor model visitor
	 */
	@Override
	public void accept(ModelVisitor visitor, Model m ) {
		for (QualityVariable var : this.getChildren()){
			var.accept(visitor, m);
		}
		visitor.visit(this);

	}
	/**
	 * Traverses the model recursively from a AND_refinement expression  to its children and to the leaf quality variables of the model to check for cyclic dependencies between quality variables.
	 * @param m semantic model obtained from parsing.
	 * @throws CyclicDependencyException if there exist a cyclic dependency between quality variables.
	 */
	@Override
	public void getCyclicDependentVariables(Model m) throws CyclicDependencyException {
		 this.definition.getCyclicDependentVariables(m);
	}
	@Override
	public double getParamExpressionValue(Model m) {
		// TODO Auto-generated method stub
		return 0;
	}


}
