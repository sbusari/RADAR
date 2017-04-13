package uk.ac.ucl.cs.radar.model;

enum UnaryOperator {
	NEG ("-"),
	POS ("+"),
	NOT ("!");
	private String unaryOperatorValue;
	UnaryOperator(String unaryOperatorValue) {
        this.unaryOperatorValue = unaryOperatorValue;
    }
    public String getUnaryOperatorValue() {
        return unaryOperatorValue;
    }
}
