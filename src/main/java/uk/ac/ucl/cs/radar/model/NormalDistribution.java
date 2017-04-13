package uk.ac.ucl.cs.radar.model;

import uk.ac.ucl.cs.radar.exception.ParameterDistributionException;

class NormalDistribution extends Distribution{

	private double mean, sd;
	int N;
	public NormalDistribution(){
		
	}
	public NormalDistribution(int simulation){
		
	}
	public NormalDistribution(double mean, double sd, int simulation){
		this.mean =mean;
		this.sd =sd;
		N= simulation;
	}
	public double [] simulate (){
		return  normalDistribution(mean, sd, N);
	}
	
	@Override
	public double[] simulate(Solution s) {
		return  normalCIDistribution(mean,sd, N);
	}
	
	@Override
	public void getCyclicDependentVariables(Model m) {
		
	}
	
	@Override
	public double getParamExpressionValue(Model m) throws ParameterDistributionException {
		throw new RuntimeException ("Normal distribution cannot be used as an argument for another distribution.");
	}
}
