package uk.ac.ucl.cs.radar.scale.test;
import uk.ac.ucl.cs.radar.model.AnalysisResult;
import uk.ac.ucl.cs.radar.model.Model;
import uk.ac.ucl.cs.radar.model.ModelSolver;
import uk.ac.ucl.cs.radar.model.Parser;
import uk.ac.ucl.cs.radar.utilities.Helper;

public class RADAR_Performance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
    		String outputDir= "/Users/INTEGRALSABIOLA/Documents/JavaProject/RADAR/uk.ac.ucl.cs.examples/";
    		String [] models = new String [] {"CBA", "FDM", "BSPDM", "ECS", "SAS"};
    		Integer []simArray = new Integer [] {1000, 10000, 100000, 1000000};
    		for (String m : models ){
    			for (int i =0; i < 2; i++){
        			for (Integer sim : simArray){
        				String modelPath = outputDir + m +"/" + m + ".rdr";
        				new RADAR_Performance().analyseRadarModel (modelPath,sim,outputDir);
            		}
        		}
			}
    				    		
    	}catch (Exception e){
    		System.out.print("Error: ");
    		System.out.println(e.getMessage());
    	}
	}
	Model loadModel (String modelPath, int nbr_sim, String infoObj,String subGraphObj) throws Exception{
		//4. when parse is specified quickly parse and write a message that the model is parsed.
		Model semanticModel =null;
		try {
			semanticModel = new Parser().parseCommandLineModel(modelPath.trim(), nbr_sim, infoObj,subGraphObj);
		}catch (RuntimeException re){
			throw new RuntimeException( "Error: "+ re.getMessage());
		}
		return semanticModel;
		
	}
	void analyseRadarModel (String modelPath , int nbr_sim, String outputDir){
		try {
    		// get sematic model from model file
    		Model semanticModel = loadModel (modelPath,nbr_sim, "", "" );
    		// analyse model
    		AnalysisResult result = ModelSolver.solve(semanticModel);  		
			String analysisResult = result.analysisToString();			
			String modelResultPath = outputDir+ nbr_sim+ "/"+semanticModel.getModelName() + "/Performance/AnalysisResult/";			
			Helper.printResults (modelResultPath , analysisResult, semanticModel.getModelName() +".out", true);
			
			String referenceDecisions = result.getReferenceDecisions();
			String referencePath = outputDir+ nbr_sim+ "/";
			Helper.printResults (referencePath , referenceDecisions, semanticModel.getModelName() +".ref", true);
			
			long runtime = result.getRunTime();
			String runTimePath = outputDir+ nbr_sim+ "/";
			Helper.printResults (runTimePath , String.valueOf(runtime), semanticModel.getModelName() +".time", true);
			
    		System.out.println("Finished with " +modelPath + " for simulaion run " + nbr_sim);
    	}catch (Exception e){
    		System.out.print("Error: ");
    		System.out.println(e.getMessage());
    	}
	}

}
