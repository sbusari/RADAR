package uk.ac.ucl.cs.radar.model;

import uk.ac.ucl.cs.radar.exception.ParameterDistributionException;

class NormalCIDistribution extends Distribution {

	private double a, b;
	int N;
	public NormalCIDistribution(){}
	public NormalCIDistribution(double a, double b, int simulation){
		this.a =a;
		this.b =b;
		N= simulation;
	}
	double [] simulate (){
		return  normalCIDistribution(a,b, N);
	}
	
	@Override
	public double[] simulate(Solution s) {
		return  normalCIDistribution(a,b, N);
	}
	
	@Override
	public void getCyclicDependentVariables(Model m) {
	
	}
	
	@Override
	public double getParamExpressionValue(Model m) throws ParameterDistributionException {
		throw new RuntimeException ("Binomial distribution cannot be used as an argument for another distribution.");
	}
}
