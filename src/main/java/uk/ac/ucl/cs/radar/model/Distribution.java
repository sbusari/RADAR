package uk.ac.ucl.cs.radar.model;
import java.util.List;


import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.GeometricDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.TriangularDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.random.GaussianRandomGenerator;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;

import uk.ac.ucl.cs.radar.utilities.ConfigSetting;

/**
 * @author Saheed Busari and Emmanuel Letier
 */
abstract class Distribution extends Expression {
	List<Expression> distributionParamter;
	RandomGenerator rg; 
	QualityVariable parent;
	public Distribution(){
		rg = new JDKRandomGenerator();
		if (ConfigSetting.SEED != 0){
			rg.setSeed(ConfigSetting.SEED);
		}
	}
	/**
	 * Returns the parent of a distribution object.
	 * @return a quality variable that is a parent of a distribution object.
	 */
	public QualityVariable getParent() {
		return parent;
	}
	/**
	 * Adds the parent of a distribution object.
	 * @param parent the quality variable that is a parent of the distribution object.
	 */
	public void setParent(QualityVariable parent) {
		this.parent = parent;
	}
	/**
	 * Visits the children of AND_Refinement to generate the variable dependency graph.
	 * @param m parsed decison model.
	 * @param visitor model visitor
	 */
	public void accept(ModelVisitor visitor, Model m) {
		
	}
	/**
	 * Constructs a solution instance for a distribution object.
	 * @param m parsed decison model.
	 * @return a set of solutions contructed for a distribution .
	 */
	@Override
	public SolutionSet getAllSolutions(Model m){
		Solution s = new Solution();
		SolutionSet result = new SolutionSet();
		result.add(s);
		return result;
	}
	/**
	 * Generate simulation values for a deterministic distribution.
	 * @param value the numeric value of the distribution
	 * @param sampleSize the number of simulation.
	 * @return an array of simulation values.
	 */
	protected double [] deterministicDistribution (double value, int sampleSize){
		double[] sample = new double[sampleSize];
		for (int i = 0; i < sample.length; ++i) {
		   sample[i] =value;
		}
		return sample;
	}
	/**
	 * Generate simulation values for a random distribution.
	 * @param sampleSize the number of simulation.
	 * @return an array of simulation values.
	 */
	protected double [] randomDistribution (int sampleSize){
			GaussianRandomGenerator generator = new GaussianRandomGenerator(rg);
			double[] sample = new double[sampleSize];
			for (int i = 0; i < sample.length; ++i) {
			   sample[i] = generator.nextNormalizedDouble();
			}
			return sample;
	 }
	/**
	 * Generate simulation values for a normal distribution.
	 * @param mean the  mean of the distribution.
	 * @param sd the standard deviation of the distribution.
	 * @param sampleSize the number of simulation.
	 * @return an array of simulation values.
	 */
	protected double [] normalDistribution ( double mean, double sd, int sampleSize){
		boolean zeros = false;
    	if (mean == 0 && sd == 0){
    		zeros = true;
		}
    	NormalDistribution generator = null;
    	generator = zeros == false?	new NormalDistribution(rg, mean, sd): null;
    	
		double[] sample = new double[sampleSize];
		for (int i = 0; i < sample.length; ++i) {
			if (zeros){
				sample[i] = 0;
			}else{
				sample[i] = generator.sample();
			}
		   
		}
		return sample;
    }
	/**
	 * Generate simulation values for a normal distribution with confidence interval.
	 * @param a the lower bound of the distribution.
	 * @param b the upper bound of the distribution.
	 * @param sampleSize the number of simulation.
	 * @return an array of simulation values.
	 */
	protected double [] normalCIDistribution ( double a, double b, int sampleSize){
    	boolean zeros = false;
    	if (a == 0 && b == 0){
    		zeros = true;
		}
    	double mean = (a+b)/2;
    	double sd = Math.abs((b - a)/3.29);
    	NormalDistribution generator = null;
    	generator = zeros == false?	new NormalDistribution(rg, mean, sd): null;
		double[] sample = new double[sampleSize];
		for (int i = 0; i < sample.length; ++i) {
			if (zeros){
				sample[i] = 0;
			}else{
				 sample[i] = generator.sample();
			}
		  
		}
		return sample;
 	}
	/**
	 * Generate simulation values for a exponential distribution.
	 * @param mean the mean of the distribution.
	 * @param sampleSize the number of simulation.
	 * @return an array of simulation values.
	 */
	protected double [] exponentialDistribution ( double mean, int sampleSize){
		ExponentialDistribution generator = new ExponentialDistribution(rg, mean);
		double[] sample = new double[sampleSize];
		for (int i = 0; i < sample.length; ++i) {
		   sample[i] = generator.sample();
		}
		return sample;
    }
	/**
	 * Generate simulation values for a binomial distribution.
	 * @param trials the number of trials.
	 * @param prob the probability of success.
	 * @param sampleSize the number of simulation.
	 * @return an array of simulation values.
	 */
    public double [] binomialDistribution ( int trials, double prob, int sampleSize){
		BinomialDistribution generator = new BinomialDistribution(rg, trials, prob);
		double[] sample = new double[sampleSize];
		for (int i = 0; i < sample.length; ++i) {
		   sample[i] = (double)generator.sample();
		}
		return sample;
    }
    /**
	 * Generate simulation values for a geometric distribution.
	 * @param prob the probability of success.
	 * @param sampleSize the number of simulation.
	 * @return an array of simulation values.
	 */
    protected double [] geometricDistribution ( double prob, int sampleSize){
		GeometricDistribution generator = new GeometricDistribution(rg, prob);
		double[] sample = new double[sampleSize];
		for (int i = 0; i < sample.length; ++i) {
		   sample[i] = (double)generator.sample();
		}
		return sample;
    }
	/**
	 * Generate simulation values for a uniform distribution.
	 * @param lower the lower bound of the distribution.
	 * @param upper the upper boudn of the distribution.
	 * @param sampleSize the number of simulation.
	 * @return an array of simulation values.
	 */
    protected double [] uniformDistribution ( double lower, double upper, int sampleSize){
    	boolean zeros = false;
    	if (lower == 0 && upper == 0){
    		zeros = true;
		}
    	UniformRealDistribution generator =  null;
    	generator = zeros == false?	new UniformRealDistribution(rg, lower, upper): null;
		double[] sample = new double[sampleSize];
		for (int i = 0; i < sample.length; ++i) {
			if (zeros){
				sample[i] = 0;
			}else{
				sample[i] = (double)generator.sample();
			}
		   
		}
		return sample;
 	}
	/**
	 * Generate simulation values for a triangular distribution.
	 * @param lower the lower bound of the distribution.
	 * @param mode the mode of the distribution.
	 * @param upper the upper bound of the distribution.
	 * @param sampleSize the number of simulation.
	 * @return an array of simulation values.
	 */
    protected double [] triangularDistribution ( double lower, double mode, double upper, int sampleSize){
    	boolean zeros = false;
    	if (lower == 0 && upper == 0 &&  mode == 0){
    		zeros = true;
		}
		TriangularDistribution generator = null;
		generator = zeros == false?new TriangularDistribution(rg, lower,mode, upper): null;
		double[] sample = new double[sampleSize];
		for (int i = 0; i < sample.length; ++i) {
			if (zeros){
				sample[i] = 0;
			}else{
				sample[i] = (double)generator.sample();
			}
		 
		}
		return sample;
    }
	    
}
