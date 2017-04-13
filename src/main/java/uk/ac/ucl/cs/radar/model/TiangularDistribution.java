package uk.ac.ucl.cs.radar.model;

import uk.ac.ucl.cs.radar.exception.ParameterDistributionException;

class TriangularDistribution extends Distribution {

	private double lower, mode, upper;
	int N;
	public TriangularDistribution(){}
	public TriangularDistribution (double lower, double mode, double upper, int simulation){
		this.lower =lower;
		this.mode =mode;
		this.upper = upper;
		N =simulation;
	}
	double [] simulate (){
		return  triangularDistribution(lower,mode, upper, N);
	}
	@Override
	public double[] simulate(Solution s) {
		return  triangularDistribution(lower,mode, upper, N);
	}
	@Override
	public void getCyclicDependentVariables(Model m) {
	}
	@Override
	public double getParamExpressionValue(Model m)
			throws ParameterDistributionException {
		throw new RuntimeException ("Triangular distribution cannot be used as an argument for another distribution.");
	}
	
}
