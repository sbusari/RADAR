package uk.ac.ucl.cs.radar.model;

import uk.ac.ucl.cs.radar.exception.ParameterDistributionException;

class BinomialDistribution extends Distribution {
	private double prob; 
	private int trials;
	int N;
	public BinomialDistribution (){
		
	}
	public BinomialDistribution (int trials, double prob,int simulation){
		this.prob = prob;
		this.trials =trials;
		this.N = simulation;
	}
	double [] simulate (){
		return  binomialDistribution(trials, prob, N);
	}
	@Override
	public double[] simulate(Solution s) {
		return  binomialDistribution(trials, prob, N);
	}
	@Override
	public void getCyclicDependentVariables(Model m) {
	}
	@Override
	public double getParamExpressionValue(Model m)
			throws ParameterDistributionException {
		throw new RuntimeException ("Binomial distribution cannot be used as an argument for another distribution.");
	}


}
