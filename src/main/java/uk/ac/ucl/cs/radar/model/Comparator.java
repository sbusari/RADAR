package uk.ac.ucl.cs.radar.model;

enum Comparator {
	LESS ("<"),
	GRET (">"),
	LEQ ("<="),
	GEQ (">=");
	private String comparatorValue;
	
	Comparator(String comparatorValue) {
        this.comparatorValue = comparatorValue;
    }
	
    public String getComparatorValue() {
        return comparatorValue;
    }
}
