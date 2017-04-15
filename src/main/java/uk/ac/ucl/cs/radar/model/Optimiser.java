package uk.ac.ucl.cs.radar.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * Computes the pareto optimal solutions. A solution is said to be Pareto-optimal if there is no other solution that is better on all objectives simultaneously.
 */
public class Optimiser {
	 /**
	 * @param s1 objective values for the first solution.
	 * @param s2 objective values for the second solution.
	 * @param objMargins objective margins to reduce simulation error.
	 * @return -1 if solution 1 dominates solution 2, and 1 if solution 2 dominates solution 1, and 0 if neither solutions dominated each other.
	 */
	int dominate (double[] s1, double [] s2, double [] objMargins){
	    int dominate1 = 0 ; //dominate1 : Solution 1 domainates solution 2 for an objective
	    int dominate2 = 0 ; //dominate2 : Solution 2 domainates solution 1 for an objective
	    int result;     
	    double value1, value2;
	    for (int i = 0; i < s1.length; i++) {
	    	value1 = s1[i];
	    	value2 = s2[i];
	    	double margin = objMargins [i];
	    	if (value1 < value2 + margin ) {
	    		result = -1;
	    	} else if (value1 > value2 + margin  )  {
	    		result = 1;
	    	} else {
	    		result = 0;
	    	}
	    	if (result == -1) {
	    		dominate1 = 1;
	    	}
	    	if (result == 1) {
	    		dominate2 = 1;           
	    	}
	    }         
	    if (dominate1 == dominate2) {            
	      return 0; //No one dominate the other
	    }
	    if (dominate1 == 1) {
	      return -1; // solution1 dominate
	    }
	    return 1;    // solution2 dominate 
	}
	 /**
	  * @param evaluatedSolutions list of simulated solutions and their objective values.
	  * @param objs model objectives.
	 * @return the set of all non-domimated (pareto optimal) solutions for the decision model.
	 */
	public  Map<Solution, double[]> getParetoSet (Map<Solution, double[]> evaluatedSolutions, List<Objective> objs){
		
		Map<Solution, double[]> result = new LinkedHashMap<Solution, double[]>();
		double [] objMargins = new double [objs.size()];
		for (int i = 0; i < objs.size() ; i ++){
			objMargins[i] =objs.get(i).getMargin();
		}
		List<Solution> solutions = new ArrayList<Solution>(evaluatedSolutions.keySet());
		List<double[]> objectiveValues = new ArrayList<double[]> (evaluatedSolutions.values());
		boolean [] isPareto =  new boolean[evaluatedSolutions.size()];
		Arrays.fill(isPareto, Boolean.TRUE);
		int i =0;
		while (i < (objectiveValues.size()-1)){
			int j = i+1;
			while (isPareto[i] && j < objectiveValues.size()){
				int dominate =0;
				dominate = dominate(objectiveValues.get(i),objectiveValues.get(j), objMargins);
		        if (dominate == -1){
		        	 isPareto[j] = Boolean.FALSE; 
		        }else if (dominate == 1){	
		        	 isPareto[i] = Boolean.FALSE; 
		        }
		        j++;
			}			
			i++;				
		}
		for (int k =0 ; k < isPareto.length; k++){
			if(isPareto[k]){
				result.put(solutions.get(k),objectiveValues.get(k));
			}
		}
		return result;
	}
}
