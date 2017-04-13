package uk.ac.ucl.cs.radar.model;

import uk.ac.ucl.cs.radar.utilities.Statistics;

class Probability extends Statistic {
	/**
	 * left variable of an expression
	 */
	Identifier leftVarName;
	/**
	 * scaler value on the right of the expression
	 */
	private double x_scaler;
	/**
	 * Stores a boolean value that tells if a variable is scaler
	 */
	boolean xIsAScaler;
	/**
	 * An arithmetic symbol to determine how the expression is compared
	 */
	Comparator comparator;
	public Probability(){}
	
	public void setLeftVarName (Identifier varName){
		leftVarName =varName;
	}
	
	public Identifier getLeftVarName (){
		return leftVarName;
	}
	
	public void xIsAScaler(boolean isXScalerValue){
		xIsAScaler = isXScalerValue;
	}
	
	public void setScalerValue (double x){
		x_scaler =x;
	}
	
	public void setComparator (String comparator){
		switch(comparator){
			case "<": this.comparator = Comparator.LESS; break;
			case ">": this.comparator = Comparator.GRET; break;
			case "<=": this.comparator = Comparator.LEQ; break;
			case ">=": this.comparator = Comparator.GEQ; break;
		}
	}
	
	@Override
	double evaluate(Solution s, QualityVariable var) {
		double [] simData = var.getSimData(s);
		double [] booleanData = applyComparator (simData);
		return new Statistics(booleanData).computeMean();
	}
	
	double [] applyComparator (double[] simData){
		for (int i =0 ; i < simData.length; i ++){
			switch (comparator.getComparatorValue()){
				case "<" : {
					simData[i] = (simData[i] < x_scaler)? 1:0;
				}; break;
				case ">" : {
					simData[i] = (simData[i] > x_scaler)? 1:0;
				}; break;
				case "<=" : {
					simData[i] = (simData[i] <= x_scaler)? 1:0;
				}; break;
				case ">=" : {
					simData[i] = (simData[i] >= x_scaler)? 1:0;
				}; break;
			}
		}
		return simData;
	}

}
