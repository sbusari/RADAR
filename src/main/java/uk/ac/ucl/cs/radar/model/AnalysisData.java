package uk.ac.ucl.cs.radar.model;
public class AnalysisData {
	
	public AnalysisData(){
		
	}
	String outputDirectory;
	String problemName;
	private int noOfSimulation;
	/**
	 * Sets the number of simulation.
	 * @param noOfSimulation number of simulation.
	 */
	public void setSimulationNumber(int noOfSimulation) {
		this.noOfSimulation = noOfSimulation;
	}
	/**
	 * @return the number of simulation.
	 */
	public int getSimulationNumber() {
		return noOfSimulation;
	}
	/**
	 * Sets the results output directory.
	 * @param outputDirectory output directory.
	 */
	public void setOutputDirectory(String outputDirectory ){
		this.outputDirectory = outputDirectory;
	}
	/**
	 * @return the output directory.
	 */
	public String getOutputDirectory(){
		return outputDirectory;
	}
	/**
	 * Sets the name of the model problem.
	 * @param problemName the name of model problem.
	 */
	public void setProblemName(String problemName ){
		this.problemName = problemName;
	}
	/**
	 * @return the the name of the model problem.
	 */
	public String getProblemName(){
		return problemName;
	}



}
