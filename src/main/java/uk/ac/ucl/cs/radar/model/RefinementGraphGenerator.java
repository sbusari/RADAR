package uk.ac.ucl.cs.radar.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class RefinementGraphGenerator implements ModelVisitor {

	/**
	 * The dot string to be generated and used by Graphviz for visualisation
	 */
	private String dotString =""; 
	/**
	 * A counter used to give unique label to each refinement
	 */
	private int refCounter ; 
	/**
	 * Stores the list of visited model elements 
	 */
	private List<ModelVisitorElement> visited;
	/**
	 * Stores the list of edges between nodes. Used to check for duplicate edges during model traversal and dot-string construction
	 */
	private List<String> edges; 
	/**
	 * Stores the decision ID of an ANDRef A so that the decisionID can be re-used by other ANDRefs B that share the same parent with ANDRef A.
	 */
	private Map<AND_Refinement, Integer> andRefDecisionID;
	/**
	 * The objective from which a generated goal-graph starts from
	 */
	Objective subGraphObjective;
	/**
	 * 
	 */
	boolean addObjectiveLabel = true;
	/**
	 * Constructs a Refinement Graph Generator.
	 * @param subGraphObj subgraph objective used to restrict the genration of a goal graph to a particular objective.
	 */
	public RefinementGraphGenerator(Objective subGraphObj){
		visited = new ArrayList<ModelVisitorElement>();
		andRefDecisionID = new LinkedHashMap<AND_Refinement, Integer>();
		edges = new ArrayList<String>();
		subGraphObjective = subGraphObj;
		dotString += "digraph G { \n";
		dotString += "rankdir = BT \n";
	}
	/**
	 *@param m semantic model obtained from parsing.
	 *@return the DOT format of the AND/OR goal graph.
	 */
	String getDotString(Model m){
		for (String undefinedVar : m.getUndefinedQualityVariables()){
			dotString += undefinedVar + " [shape = plaintext, fontcolor =red]";
		}
		
		dotString += "}";
		return dotString;
	}
	/**
	 * Implementation of the Model visitor to deal with specifics of what to do when we visit a Model instance.
	 * @param m semantic model obtained after parsing.
	 */
	@Override
	public void visit(Model m) {

	}
	/**
	 * Implementation of the Objective visitor to deal with specifics of what to do when we visit an Objective instance.
	 * @param obj objective to visit.
	 */
	@Override
	public void visit(Objective obj) {
		if (!visited.contains(obj)){
			// when subgraph is specified, we do not want the objective to show, it starts from the quality variable.
			String objDotString = obj.getLabel() + " [shape = box] \n";
			QualityVariable qvReferTo = obj.getQualityVariable();
			if (subGraphObjective != null){
					if (!subGraphObjective.getQualityVariable().getLabel().equals(obj.getQualityVariable().getLabel())){
						dotString +=  objDotString;
						// no edge when objective name equals var it refers to.
						if (!obj.getLabel().equals(qvReferTo.getLabel())){
							String childDotString = qvReferTo.getLabel() + "->" +  "\""+  obj.getLabel() +  "\"" + "\n";
							dotString +=  childDotString;
						}
					}
			}
			else{
					dotString +=  objDotString;
					// no edge when objective name equals var it refers to.
					if (!obj.getLabel().equals(qvReferTo.getLabel())){
						String childDotString = qvReferTo.getLabel() + "->" +  "\""+  obj.getLabel() +  "\"" + "\n";
						dotString +=  childDotString;
					}
			}
			visited.add(obj);
		}
	}
	/**
	 * Implementation of the Statistic visitor to deal with specifics of what to do when we visit an Statistic instance.
	 * @param stat statistic to visit.
	 */
	@Override
	public void visit(Statistic stat) {
		
	}
	/**
	 * Implementation of the QualityVariable visitor to deal with specifics of what to do when we visit an QualityVariable instance.
	 * @param var qualityVariable to visit.
	 */
	@Override
	public void visit(QualityVariable var) {
		if (!visited.contains(var)){
			String varDotString =  "\""+ var.getLabel() +  "\"" + "[shape = box, style = rounded] \n";
			dotString +=  varDotString;
			// we do not want a qv whose parent is objective to be added here
			// implement the same for unary
			if (var.getDefinition() != null && var.getDefinition().getParent() != null 
					&& (var.getDefinition() instanceof BinaryExpression || var.getDefinition() instanceof UnaryExpression) ){ 
				String refID = "AndRef" + refCounter;
				refCounter++;
				dotString += refID + "[shape = point] \n";
				if (var.getDefinition() instanceof BinaryExpression){
					String parentLabel = ((BinaryExpression)var.getDefinition()).getParent().getLabel();
					String andDotString = refID + "->" +  "\""+  parentLabel +  "\"" + "\n";
					for (QualityVariable child : ((BinaryExpression)var.getDefinition()).getQualityVariable()){
						String childLabel = child.getLabel();
						String childDotString =  "\""+ childLabel + "\"" + "->" + refID + " [dir = none] \n";
						if (!edges.contains(childDotString)){
							andDotString += childDotString;
							edges.add(childDotString);
						}
					}
					dotString +=  andDotString;
					visited.add(((BinaryExpression)var.getDefinition()));
				}else{// unary expr
					String parentLabel = ((UnaryExpression)var.getDefinition()).getParent().getLabel();
					String andDotString = refID + "->" +  "\""+  parentLabel +  "\"" + "\n";
					for (QualityVariable child : ((UnaryExpression)var.getDefinition()).getQualityVariable()){
						String childLabel = child.getLabel();
						String childDotString =  "\""+ childLabel + "\"" + "->" + refID + " [dir = none] \n";
						if (!edges.contains(childDotString)){
							andDotString += childDotString;
							edges.add(childDotString);
						}
					}
					dotString +=  andDotString;
					visited.add(((UnaryExpression)var.getDefinition()));
				}
			}
			visited.add(var);
		}
	}
	/*for generating only AND/OR refinements.
	@Override
	public void visit(QualityVariable var) {
		if (!visited.contains(var)){
			if (var.getDefinition() != null && var.getDefinition().getParent() != null 
					&& (var.getDefinition() instanceof OR_Refinement) ){ 
				String varDotString =  "\""+ var.getLabel() +  "\"" + "[shape = box, style = rounded] \n";
				dotString +=  varDotString;
				
			}
			visited.add(var);
		}
	}*/
	/*
	 * gets ANDRefinemnet decision ID if ANDRefinement parent already visited by another ANDRefinement that share the same parent.
	 */
	Integer getANDRefID (AND_Refinement andRef){
		Integer result =null;
		for (Map.Entry<AND_Refinement, Integer> entry:andRefDecisionID.entrySet()){
			if (entry.getKey().getParent().getLabel().equals(andRef.getParent().getLabel())){
				result = entry.getValue();
			}
		}
		return result;
	}
	// this include decisions between quality variables
	/**
	 * Implementation of the AND_Refinement visitor to deal with specifics of what to do when we visit an AND_Refinement instance.
	 * @param andRef AND_refinement to visit.
	 */
	@Override
	public void visit(AND_Refinement andRef) {
		if (!visited.contains(andRef) ){
			String refID = "AndRef" + refCounter;
			refCounter++;
			dotString += refID + "[shape = point] \n";
			
			String decisionID ="";
			Integer andRefID = getANDRefID(andRef);
			if ( andRefID != null){
				decisionID = ""+andRefID; 
			}else{
				int ID = refCounter;
				decisionID = ""+ID;
				andRefDecisionID.put(andRef, ID);
			}
			dotString += decisionID + "[label=\""  + andRef.getDecisionNameAndRefRefersTo()   +"\", shape = polygon, sides =8]"; 
			String parentLabel = andRef.getParent().getLabel();
			String decisionToParent =  decisionID + "->" +  "\""+  parentLabel +  "\"" + "\n";
			if (!edges.contains(decisionToParent)){
				dotString += decisionToParent;
				edges.add(decisionToParent);
			}
			String andDotString = refID + "->" +    decisionID + "\n";
			for (QualityVariable child : andRef.getChildren()){
				String childLabel = child.getLabel();
				dotString += "\"" +  childLabel + "\"" + "[shape = box, style = rounded] \n";
				String childDotString =  "\""+ childLabel + "\"" + "->" + refID + " [dir = none] \n";
				if (!edges.contains(childDotString)){
					andDotString += childDotString;
					edges.add(childDotString);
				}
				
			}
			dotString +=  andDotString;
			visited.add(andRef);
			
		}
	}
	// this does not include decisions betwee quality variables
/*	@Override
	public void visit(AND_Refinement andRef) {
		if (!visited.contains(andRef) ){
			String refID = "AndRef" + refCounter;
			refCounter++;
			dotString += refID + "[shape = point] \n";
			String parentLabel = andRef.getParent().getLabel();
			String andDotString = refID + "->" +  "\""+  parentLabel +  "\"" + "\n";
			for (QualityVariable child : andRef.getChildren()){
				String childLabel = child.getLabel();
				dotString += "\"" +  childLabel + "\"" + "[shape = box, style = rounded] \n";
				String childDotString =  "\""+ childLabel + "\"" + "->" + refID + " [dir = none] \n";
				if (!edges.contains(childDotString)){
					andDotString += childDotString;
					edges.add(childDotString);
				}
				
			}
			dotString +=  andDotString;
			visited.add(andRef);
			
		}
	}*/
	/**
	 * Implementation of the OR_Refinement visitor to deal with specifics of what to do when we visit an OR_Refinement instance.
	 * @param orRef OR_Refinement to visit.
	 */
	@Override
	public void visit(OR_Refinement orRef) {

	}



}
