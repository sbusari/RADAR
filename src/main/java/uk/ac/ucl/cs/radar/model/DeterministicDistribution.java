package uk.ac.ucl.cs.radar.model;

import uk.ac.ucl.cs.radar.exception.ParameterDistributionException;

class DeterministicDistribution extends Distribution {

	private double value;
	int N;
	public DeterministicDistribution(double value, int simulation){
		this.value =value;
		N = simulation;
	}
	@Override
	public double[] simulate(Solution s) {
		return  deterministicDistribution(value, N);
	}
	@Override
	public void getCyclicDependentVariables(Model m) {
	}
	@Override
	public double getParamExpressionValue(Model m) throws ParameterDistributionException {
		return value;
	}


}
