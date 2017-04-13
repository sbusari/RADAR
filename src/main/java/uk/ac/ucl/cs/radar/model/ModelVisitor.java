package uk.ac.ucl.cs.radar.model;

interface ModelVisitor {

	void visit(Model m);
	void visit(Objective obj);
	void visit(Statistic stat);
	void visit(QualityVariable var);
	void visit(AND_Refinement andRef);
	void visit(OR_Refinement orRef);
}
