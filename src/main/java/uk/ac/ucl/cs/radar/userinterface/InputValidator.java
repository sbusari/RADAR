package uk.ac.ucl.cs.radar.userinterface;

import java.io.File;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTextField;

import uk.ac.ucl.cs.radar.model.Model;

public class InputValidator {
	
	
	public static String verifyEmptyField (JComponent input, String fieldName, String dataType){
		String message ="";
	    if (((JTextField) input).getText().isEmpty()){
	    	message += "'"+fieldName + "' must be specified. \n";
	    }
	   return message;
	}
	public static void objectiveExist (Model model, String objective) throws Exception{
		
		if (objective != null){
			model.objectiveExist(objective);
		}
	}
	public static void validateModelPath (String modelPath) throws Exception{
		if (modelPath != null && !modelPath.isEmpty()){
			File modelFile = new File (modelPath.trim());
    		if (!modelFile.exists()){
    			throw new Exception ("Warning: "+ "The file '"+ modelPath+ "' does not exist."); 
    		}
		}
	}
	public static void validateOutputPath (String outputPath) throws Exception{
		if (outputPath != null){
			File modelFile = new File (outputPath.trim());
    		if (!modelFile.exists()){
    			throw new Exception (""+ "The file '"+ outputPath+ "' does not exist."); 
    		}
		}
	}
	
	public static void validateSbseParameters (boolean defaultAlgorithmParameter,List<String> solve,List<String> algorithmParameter ) throws Exception{
		if (!solve.contains("ExhaustiveSearch") && algorithmParameter.size() <= 0 && defaultAlgorithmParameter == false ){
			throw new Exception ("Warning: "+ "Algorithm parameters such population size, crossover, mutation and max evaluaitons must be specified for NSGAII, SPEA2, IBEA, MoCell, RandomSearch and PAES using the command '--param' or '--param-default'." ); 
		}
		if ( solve.contains("ExhaustiveSearch") && ( algorithmParameter.size() > 0 || defaultAlgorithmParameter == true )){
			throw new Exception ("Warning: "+ " ExhaustiveSearch does not require algorithm paramters command '--param' or '--param-default'." ); 
		}
		if (solve.contains("ExhaustiveSearch") && ( solve.contains("NSGAII") ||  solve.contains("SPEA2") ||  solve.contains("IBEA") ||  solve.contains("MoCell") ||  solve.contains("PAES") ||  solve.contains("RandomSearch"))){
			throw new Exception ("Warning: "+ " Exact and approximate algorithms cannot be run simulateneously." ); 
		}
	}
	
	public static void validateSolveNotNull (List<String> solve) throws Exception{
		if ( !solve.contains("ExhaustiveSearch") && !solve.contains("NSGAII") &&  !solve.contains("SPEA2") &&  !solve.contains("IBEA") &&  !solve.contains("MoCell") &&  !solve.contains("PAES") &&  !solve.contains("RandomSearch")){
			throw new Exception ("Warning: "+ "RADAR only implements the following algorithms: " + "ExhaustiveSearch, NSGAII, SPEA2, IBEA, MoCell, RandomSearch and PAES " );
		}
	}
	public static void validateSbseParameterValues (List<String> algorithmParameter) throws Exception{
		String errorMessage = "";
		if (algorithmParameter.size() > 0){
			errorMessage = InputValidator.verifyFieldDataType(algorithmParameter.get(0).trim(), "population size", "Integer");
    		errorMessage += InputValidator.verifyFieldDataType(algorithmParameter.get(1).trim(), "crossover rate", "Double");
    		errorMessage += InputValidator.verifyFieldDataType(algorithmParameter.get(2).trim(), "mutation rate", "Double");
    		errorMessage += InputValidator.verifyFieldDataType(algorithmParameter.get(3).trim(), "maximum evaluation", "Integer");
    		errorMessage += InputValidator.verifyFieldDataType(algorithmParameter.get(4).trim(), "runs", "Integer");
    		
    		if (errorMessage.isEmpty()){
    			errorMessage += InputValidator.verifyFieldNonNegativeValue(algorithmParameter.get(0).trim(), "population size", "Integer");
        		errorMessage += InputValidator.verifyFieldNonNegativeValue(algorithmParameter.get(1).trim(), "crossover rate", "Double");
        		errorMessage += InputValidator.verifyFieldNonNegativeValue(algorithmParameter.get(2).trim(), "mutation rate", "Double");
        		errorMessage += InputValidator.verifyFieldNonNegativeValue(algorithmParameter.get(3).trim(), "maximum evaluation", "Integer");
        		errorMessage += InputValidator.verifyFieldNonNegativeValue(algorithmParameter.get(4).trim(), "runs", "Integer");
        		
    		}
    		if (errorMessage.isEmpty()){
    			errorMessage += InputValidator.verifyFieldValueRange(algorithmParameter.get(1).trim(), "crossover rate", "Double");
        		errorMessage += InputValidator.verifyFieldValueRange(algorithmParameter.get(2).trim(), "mutation rate", "Double");
        		
    		}
    		
    		if (!errorMessage.isEmpty()){
				throw new Exception ("Warning: "+ errorMessage);
				
    		}
    		
		}
	}
	public static String verifyFieldDataType (String input, String fieldName, String dataType){
		String message ="";
		String text = input;
		if (! text.isEmpty()){
			try 
			{
				switch (dataType){
					case "Integer" : Integer.parseInt(text);break;
					case "Double" : Double.parseDouble(text);break;
					case "String" : {
							try{
								try{
									double a = Double.parseDouble(text);
								}catch(Exception e){
									// break if it throws and excepion for matching a string, since that is what is expected.
									break;
								}
								// if no exception throw number exception.
								throw new NumberFormatException();
							}catch(NumberFormatException e){
								message += "Input value for '" +fieldName+ "' must be of data type '"+ dataType +"'.\n";
							}}; break;
					default: 
				}
				
			}
			catch (Exception e) {
				message += "Input value for '" +fieldName+ "' must be of data type '"+ dataType +"'.\n"; 
		    }
		}
	   return message;
	}
	public static String verifyFieldNonNegativeValue (String input, String fieldName, String dataType){
		String message ="";
		String text = input;
		if (! text.isEmpty()){
			switch (dataType){
				case "Integer" : message+= Integer.parseInt(text) < 0 ? "Value for '"+fieldName + "' cannot be negative. \n": "" ;break;
				case "Double" : message+=  Double.parseDouble(text) < 0 ? "Value for '"+fieldName + "' cannot be negative.\n": "" ;break;
				default: 
			}
		}
		
	   return message;
	}
	public static String verifyFieldValueRange (String input, String fieldName, String dataType){
		String message ="";
		String text = input;
		if (! text.isEmpty()){
			switch (dataType){
				case "Integer" : message+= Integer.parseInt(text) < 0 || Integer.parseInt(text) >1 ? "Value for '"+fieldName + "' must be between 0 and 1. \n": "" ;break;
				case "Double" : message+=  Double.parseDouble(text) < 0 || Double.parseDouble(text) > 1 ? "Value for '"+fieldName + "' must be between 0 and 1. \n": "" ;break;
				default: 
			}
		}
	   return message;
	}

}
