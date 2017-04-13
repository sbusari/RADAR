package uk.ac.ucl.cs.radar.model;

import uk.ac.ucl.cs.radar.exception.ParameterDistributionException;

class UniformDistribution extends Distribution {

	private double lower, upper;
	int N;
	public UniformDistribution (){
		
	}
	public UniformDistribution (double lower, double upper, int simulation){
		this.lower = lower;
		this.upper=upper;
		N = simulation;
	}
	double [] simulate (){
		return  uniformDistribution(lower,upper, N);
	}
	@Override
	public double[] simulate(Solution s) {
		return  uniformDistribution(lower,upper, N);
	}
	@Override
	public void getCyclicDependentVariables(Model m) {
	}
	@Override
	public double getParamExpressionValue(Model m)
			throws ParameterDistributionException {
		throw new RuntimeException ("Uniform distribution cannot be used as an argument for another distribution.");
	}

}
