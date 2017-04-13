package uk.ac.ucl.cs.radar.model;

import java.util.List;

import uk.ac.ucl.cs.radar.information.analysis.InformationAnalysis;

class InformationValueAnalyser {
	/**
	 * Computes and store expected value of perfect information (evtpi) and expected value of partial perfect information (evppi) in the AnalysisResult  object.
	 * @param result analysis result.
	 * @param objective model objectives.
	 * @param solutions list of optimal solutions
	 * @param params list of model parameters.
	 */
	static void computeInformationValue(AnalysisResult result, Objective objective, List<Solution> solutions, List<Parameter> params){
		result.addEviObjective (objective);
		double[][] objSim = objective.getQualityVariable().simulate(solutions);
		// compute evtpi
		double evtpi = InformationAnalysis.evpi(objSim);
		result.addEVTPI(evtpi);
		// compute evppi for each quality variable in params
		for (int i=0; i <params.size(); i++){
			Parameter param = params.get(i) ;
	        double[] paramSim = param.getSimulationData();
	        double evppi = InformationAnalysis.evppi(paramSim, objSim);
	        result.addEVPPI(param.getLabel(), evppi);
		}
	}
}
