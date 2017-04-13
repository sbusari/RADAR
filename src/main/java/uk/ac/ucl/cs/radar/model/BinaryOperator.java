package uk.ac.ucl.cs.radar.model;

/**
 * @author Saheed Busari and Emmanuel Letier
 */
enum BinaryOperator {
	ADD ("+"),
	SUB ("-"),
	MUL ("*"),
	DIV ("/"),
	AND ("&&"),
	OR ("||"),
	LESS ("<"),
	GRET (">"),
	LEQ ("<="),
	GEQ(">=");
	
	private String binaryOperatorValue;
	BinaryOperator(String binaryOperatorValue) {
        this.binaryOperatorValue = binaryOperatorValue;
    }
    public String getBinaryOperatorValue() {
        return binaryOperatorValue;
    }
}
