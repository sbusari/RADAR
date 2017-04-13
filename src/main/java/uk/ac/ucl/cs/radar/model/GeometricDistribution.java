package uk.ac.ucl.cs.radar.model;

import uk.ac.ucl.cs.radar.exception.ParameterDistributionException;


class GeometricDistribution extends Distribution {
	private double prob ;
	int N;
	public GeometricDistribution (){
		
	}
	public GeometricDistribution (double prob,int simulation){
		this.prob = prob;
		N = simulation;
	}
	double [] simulate (){
		return  geometricDistribution(prob, N);
	}
	
	@Override
	public double[] simulate(Solution s) {
		return  geometricDistribution(prob, N);
	}
	@Override
	public void  getCyclicDependentVariables(Model m) {
	}
	@Override
	public double getParamExpressionValue(Model m)
			throws ParameterDistributionException {
		throw new RuntimeException ("Geometric distribution cannot be used as an argument for another distribution.");
	}
}
