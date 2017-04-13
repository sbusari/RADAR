package uk.ac.ucl.cs.radar.model;
/**
 * @author Saheed A. Busari and Emmanuel Letier
 */
 class Parameter extends QualityVariable {
	 
    private Distribution distribution;
    private double[] simData;
	/**
	 * Simulates a solution s.
	 * @param s a solution to be simulated through monte-carlo simulation.
	 * @return an array of simulated values.
	 */
    public double[] simulate(Solution s){
    	if (simData == null) simData = distribution.simulate(s);
        return simData;
    }
    /**
	 * Visits the distribution of a model Parameter to generate the variable dependency graph.
	 * @param visitor model visitor
	 * @param m semantic model obtained from parsing.
	 */
	public void accept(ModelVisitor visitor, Model m) {
		distribution.accept(visitor,m);
	}
	/**
	 * Sets the probability distribution of a model Parameter.
	 * @param distr probability distribution to set.
	 */
    public void setDistribution (Distribution distr){
    	distribution = distr;
	}
    /**
	 * @return probability distribution of a model Parameter.
	 */
	public Distribution getDistribution (){
		return distribution;
	}
	 /**
	 * @return an array of simulated values for a probability distribution of a model Parameter.
	 */
	public double[] getSimulationData (){
		return simData;
	}

}
