package uk.ac.ucl.cs.radar.model;

interface ModelVisitorElement {

	void accept(ModelVisitor visitor, Model m);
}
