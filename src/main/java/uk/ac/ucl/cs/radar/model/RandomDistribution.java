package uk.ac.ucl.cs.radar.model;

import uk.ac.ucl.cs.radar.exception.ParameterDistributionException;

class RandomDistribution extends Distribution {
	int N;
	public RandomDistribution (){
		
	}
	public RandomDistribution (int nbr_simulation){
		N = nbr_simulation;
	}
	double [] simulate (){
		return  randomDistribution( N);
	}
	@Override
	public double[] simulate(Solution s) {
		return  randomDistribution(N);
	}
	@Override
	public void getCyclicDependentVariables(Model m) {
	}
	@Override
	public double getParamExpressionValue(Model m) throws ParameterDistributionException {
		throw new RuntimeException ("Random distribution cannot be used as an argument for another distribution.");
	}
}
