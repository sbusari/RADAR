package uk.ac.ucl.cs.radar.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class SolutionSet {

	private Set<Solution> solutions;

	public SolutionSet(){
		solutions = new HashSet<Solution>();
	}

	/**
	* @return the set of solutions
	*/
	Set<Solution> set(){
		return solutions;
	}
	/**
	* @return the set of solutions as a list
	*/
	public List<Solution> list(){
		return new ArrayList<Solution>(solutions);
	}
	/**
	* @return true if the set of solutions is empty, i.e. the size of the list is zero.
	*/
	boolean isEmpty(){
		return solutions.isEmpty();
	}

	/**
	 * 
	 * @return the size of the solution set.
	 */
	public int size ()
	{
		return solutions.size();
	}
	/**
	 * @param i index of solution to be obtained
	 * @return the solution at index i.
	 */
	public Solution get (int i)
	{
		return new ArrayList<Solution>(solutions).get(i);
	}
	/**
	 * Adds solution s to the set of solutions
	 * @param s solution to be added.
	 */
	public void add(Solution s){
		solutions.add(s);
	}
	/**
	 * Adds a set of solutions to an existing set of solutions
	 * @param slns set of solutions to be added.
	 */
	void addAll(SolutionSet slns){
		solutions.addAll(slns.set());
	}
	
	/**
	*  
	* The returned solution set includes the union of all non-conflicting solution pairs.
	* @param slns set of solutions to be merged.
	* @return a solution set that merges the solution set with another solution set.
	*/
	SolutionSet merge(SolutionSet slns){

		if (this.isEmpty()){return slns;}
		if (slns.isEmpty()){return this;}

		SolutionSet result = new SolutionSet();
		for (Solution s: this.set()){
			for (Solution s1: slns.set()){
				if (!conflicting(s, s1)){
					Solution newSol = s.union(s1);
					result.add(newSol);
				}
			}
		}
		return result;
	}


	/**
	* @return true if s1 and s2 have different option selection for a same decision
	*/ 
	private static boolean conflicting(Solution s1, Solution s2){
		for (Decision d: s1.decisions()){
			if (s2.selection(d) != null && !s2.selection(d).equals(s1.selection(d))) return true;
		}
		return false;
	}

}
