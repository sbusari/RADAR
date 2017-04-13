package uk.ac.ucl.cs.radar.model;

import uk.ac.ucl.cs.radar.exception.ParameterDistributionException;

class ExponentialDistribution extends Distribution {

	private double mean ;
	int N;
	public ExponentialDistribution (){
		
	}
	public ExponentialDistribution (double mean,int simulation){
		this.mean = mean;
		N = simulation;
	}
	double [] simulate (){
		return  exponentialDistribution(mean, N);
	}
	@Override
	public double[] simulate(Solution s) {
		return  exponentialDistribution(mean, N);
	}
	@Override
	public void getCyclicDependentVariables(Model m) {
	}
	@Override
	public double getParamExpressionValue(Model m)
			throws ParameterDistributionException {
		throw new RuntimeException ("Exponential distribution cannot be used as an argument for another distribution.");
	}

}
