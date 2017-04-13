package uk.ac.ucl.cs.radar.model;

import java.util.List;

/**
 * @author Saheed Busari and Emmanuel Letier
 * Given a candidate solution s, the simulator returns the values of all objectives for s.
 */
 class Simulator {
	 public Simulator(){}
	 /**
	 * Evaluates and simulates model objectives through monte-carlo simulation.
	 * @param s a solution to be simulated through monte-carlo simulation.
	 * @param objectives model objectives to simulate
	 * @param m semantic model obtained from parsing.
	 * @return an array of simulated objective values.
	 */
	public double[] evaluate (List<Objective> objectives, Solution s, Model m){
		s.setSemanticModel(m);
		double [] objectiveValues = new double [objectives.size()];
		for (int i =0; i < objectives.size(); i ++){
			Objective obj = objectives.get(i);
			double value = obj.evaluate(s);
			objectiveValues[i] = value;
		}
		return objectiveValues;
	}

	

}
