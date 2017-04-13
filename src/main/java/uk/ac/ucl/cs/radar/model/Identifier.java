package uk.ac.ucl.cs.radar.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uk.ac.ucl.cs.radar.exception.CyclicDependencyException;
import uk.ac.ucl.cs.radar.exception.ModelException;
import uk.ac.ucl.cs.radar.exception.ParameterDistributionException;
/**
 * @author Saheed Busari and Emmanuel Letier
 * This class holds the name of a quality varibale within an expression.
 */
class Identifier extends ArithmeticExpression implements ModelVisitorElement {
	/**
	 * the name of the quality variable within an expression
	 */
	private String id;
	/**
	 * the quality variable the identifier defines i.e the parent
	 */
	QualityVariable parent;
	/**
	 * the quality variable object that the identifier represents.
	 */
	QualityVariable linkedQv;
	/**
	 * Stores the name of a quality variable in an expression.
	 * @param id quality variable name in an expression.
	 */
	public void setID (String id){
		this.id = id;
	}
	/**
	 * @return the quality variable name in an expression.
	 */
	public String  getID (){
		return id;
	}
	public void setLinkedQualityVariable (QualityVariable linkedQv){
		this.linkedQv = linkedQv;
	}
	 /**
	 * Simulates a solution s.
	 * @param s a solution to be simulated through monte-carlo simulation.
	 * @return an array of simulated values.
	 */
	@Override
	public double[] simulate(Solution s) {
		Map<String, QualityVariable> qvList = s.getSemanticModel().getQualityVariables();
		QualityVariable qv = qvList.get(id);
		if (qv ==null){
			double [] sim = new double [s.getSemanticModel().getNbr_Simulation()];
			for (int i = 0; i < sim.length; ++i) {
				sim[i] =0;
			}
			s.getSemanticModel().addUndefinedQualityVariable(id);
			return sim;
			//throw new RuntimeException ("Quality variable " + id_ + " is not defined in the model. Check it is well spelt.");
		}else{
			return qv.simulate(s);
		}
	}
	/**
	 * Returns the parent of an identifier.
	 * @return a quality variable that is a parent of an expression.
	 */
	public QualityVariable getParent() {
		return parent;
	}
	/**
	 * Sets the parent of an identifier.
	 * @param parent  quality variable that is a parent of an identifier.
	 */
	public void setParent(QualityVariable parent) {
		this.parent = parent;
	}
	/**
	 * Visits the children of an identifier, but first gets the quality variable instance of the identifier.
	 * @param m semantic model obtained from parsing.
	 *@param visitor model visitor
	 */
	@Override
	public void accept(ModelVisitor visitor, Model m) {
		//undefinedQualityVariable_
		QualityVariable qv = m.getQualityVariables().get(id);
		// undefined quality variable (e.g due to wrong spelling) within an arithmtic expression could give null.
		if (qv != null){
			qv.accept(visitor, m);
		}
		
	}
	/**
	 * @return a quality variable instance of an identifier.
	 */
	@Override
	List<QualityVariable> getQualityVariable() {
		List<QualityVariable> result = new ArrayList<QualityVariable>();
		result.add(linkedQv);
		return result;
	}
	/**
	 * Traverses a quality variable instance of an identifier recursively until reaching the leaf quality variables to get all solutions.
	 * @param m semantic model obtained from parsing.
	 * @return a set of solutions constructed while traversing a quality variable instance of an identifier up to the leaf quality variables.
	 */
	@Override
	public SolutionSet getAllSolutions(Model m) {
		QualityVariable qv = m.getQualityVariables().get(id);
		if (qv == null){
			m.addUndefinedQualityVariable(id);
			return new SolutionSet();
			//throw new RuntimeException ("Quality variable " + id_ + " is not defined in the model. Check it is well spelt.");
		}
		SolutionSet solutions = qv.getDefinition().getAllSolutions(m);
		return solutions;
	}
	/**
	 * Traverses a quality variable instance of an identifier recursively until reaching the leaf quality variables to check for cyclic dependencies between quality variables.
	 * @param m semantic model obtained from parsing.
	 * @throws CyclicDependencyException if variable dependency graph is cyclic. 
	 */
	@Override
	public void getCyclicDependentVariables(Model m) throws CyclicDependencyException {
		QualityVariable qv = m.getQualityVariables().get(id);
		if (qv != null){
			for (String child : qv.getChildren()){
				if (parent.getLabel().equals(child)){
					throw new RuntimeException ("Cyclic dependency in the model between " + parent.getLabel() + " and " + id);
				}
				// is the child (NB) of qv(A) is already on stack, get that child and check if any of its child is not qv(A)
				if (isQualityVariableVisited(child, m)){
					List<String> grandChildren =  m.getQualityVariables().get(child).getChildren();
					for (String grandChild : grandChildren){
						if (id.equals(grandChild)){
							throw new CyclicDependencyException( "Cyclic dependency in the model between " + id + " and " + grandChild + "\n");
						}
					}
				}
				
			}
			m.addVisitedQualityVariable(qv);
		}else{
			//throw new RuntimeException ("Quality variable " + id_ + " is not defined in the model. Check it is well spelt.");
		}
		
	}
	/**
	 * @param m parsed decison model.
	 * @param variable quality variable name to check.
	 * @return true if a quality variable has been visited, else returns false.
	 */
	boolean isQualityVariableVisited (String variable, Model m){
		boolean result =false;
		if (m.getVisitedQualityVariable().containsKey(variable)){
			return true;
		}
		return result;
	}
	@Override
	public double getParamExpressionValue(Model m) throws ParameterDistributionException {
		QualityVariable qv = m.getQualityVariables().get(id);
		if (qv ==null){
			throw new ParameterDistributionException ("Variable "+ id +" must be initialised before using it as a parameter in a distribution.");
		}
		return qv.getParamExpressionValue(m);
	}
}
