package uk.ac.ucl.cs.radar.model;

import uk.ac.ucl.cs.radar.utilities.Statistics;

class BooleanProbability extends Statistic {
	Identifier varName;
	
	public void setVarName (Identifier varName){
		this.varName =varName;
	}
	public Identifier getVarName (){
		return varName;
	}
	public BooleanProbability() {
		
	}
	@Override
	double evaluate(Solution s, QualityVariable var) {
		double [] simData = var.getSimData(s);
		return new Statistics(simData).computeMean();
	}

}
