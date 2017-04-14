package uk.ac.ucl.cs.radar.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import uk.ac.ucl.cs.radar.userinterface.InputValidator;
import uk.ac.ucl.cs.radar.utilities.Config;
import uk.ac.ucl.cs.radar.utilities.Helper;

public class SyntheticModelGenerator {

	String outputPath;
	int nbr_decisions;
	// used to track if all decisions have been used in the model
	int decision_counter;
	//store the min no variables required in the model.
	int min_nbr_variables;
	int nbr_objectives;
	int min_nbr_options;
	int max_nbr_options;
	int loopCounter;
	static int var_index =0;
	int nbr_radar_var_ =3;
	String modelName_;
	Random rn = new Random();
	// store all model variables and their expression 
	Map<String, String> modelVariables_;
	// store model variables  for an or refinement
	Map<String, String> orRefModelVariables_;
	// store model variable and their definition whether and, or, param est.
	Map<String, String> variableDefinition_;
	List<String> decisionNames_;
	String[] opt_dir = new String[]{"Max", "Min"};
	String[] obj_statistic = new String[]{"EV", "Pr"};
	String[] var_symbols = new String[]{"X", "Y", "Z", "V", "T", "H", "M", "L", "N", "R", "S", "Q", "P"};
	String modelType_ ="simple";
	public SyntheticModelGenerator(){
		modelVariables_ = new LinkedHashMap<String, String>();
		variableDefinition_ = new LinkedHashMap<String, String>();
		decisionNames_ = new ArrayList<String>();
		orRefModelVariables_ = new LinkedHashMap<String, String>();
	}
	
	List<String> generateAllDecisionVariables(int nbr_radar_var){
		int total_var = (int)Math.pow(2,nbr_radar_var);
		List<String> result = new ArrayList<String>();
		int index=0;
		while (index < total_var ){
			java.math.BigInteger decision_var = new java.math.BigInteger(""+index);
			String padded= StringUtils.leftPad(decision_var.toString(2), 3, '0');
			result.add(padded);
			index ++;
		}
		return result;
	}
	char[] getRadarVariableDefinitionIndex (){
		int total_var = (int)Math.pow(2,nbr_radar_var_);
		int rand = getRandom(0,total_var-1);
		List<String> allDecisionVariables = generateAllDecisionVariables(total_var);
		char[] decision_var = allDecisionVariables.get(rand).toCharArray();
		return decision_var;
	}
	String getExpression(int index, String var_symbol, String left_expr ){
		StringBuilder sb = new StringBuilder();
		String and_var = "";
		String param_def = "";
		String or_ref = "";
		
		// and refinement
		and_var = var_symbol + (++ var_index);
		variableDefinition_.put(and_var, "and_def");
		String andRef =  and_var + " " + getRandomOperator() + " "; 
		sb.append(andRef);	
		min_nbr_variables--;
		
		//param estimation
		param_def =  var_symbol + (++ var_index);
		variableDefinition_.put(param_def, "param_def");
		String param_estimation = param_def + " " + getRandomOperator() + " ";
		sb.append(param_estimation);
		min_nbr_variables--;
	
		String X_def = "";
		if(nbr_decisions > 0){
			or_ref = var_symbol + (++var_index);
			variableDefinition_.put(or_ref, "or_def");
			sb.append(or_ref);
			// define the current variable here
			String decisionName = getDecisionName();
			X_def = "decision(" + decisionName  + "){\n";
			int nbr_random_option = getRandom(min_nbr_options,max_nbr_options);
			for (int i =0; i < nbr_random_option; i++){
				if(modelType_.equals("simple")){
					X_def += "\t \"O" + getDecisionID(decisionName) + "_"+ i +"\" : "+ generateRandomParameterEstimation()+ " \n";
				}else{
					X_def += "\t \"O" + getDecisionID(decisionName) + "_" + i +"\" : "+ generateRandomExpression(var_symbol)+ " \n";
				}
				
			}
			X_def += "}";
			nbr_decisions--;
			min_nbr_variables--;
		}
		
		// refine the equation to remove last oprator incase the number of decisions is reached and execution does not brack the XOR.
		String sb_toString= sb.toString();
		//System.out.print("check: " + sb_toString );
		String operators = "+/*-";
		String lastChar = sb_toString.trim().substring(sb_toString.length()-1);
		if (operators.contains(lastChar)){
			sb_toString = sb_toString.substring(0,sb_toString.length()-2);
		}
		modelVariables_.put(left_expr, sb_toString + ";");
		// add the or refinement expressions to the global variable
		for (Map.Entry<String, String> entry: orRefModelVariables_.entrySet()){
			modelVariables_.put(entry.getKey(), entry.getValue());
		}
		if(param_def != ""){modelVariables_.put(param_def,generateRandomParameterEstimation());}
		if (X_def != "" ){
			modelVariables_.put(or_ref, X_def);
		}	
		return sb.toString() + ";";
	}
	String generateRandomExpression(String var_symbol){
		String result =generateRandomParameterEstimation();
		int rand =  getRandom(0,2);
		switch (rand){
			case 0: result = generateRandomORRef(var_symbol); break;
			case 1: result =  generateRandomAndRef(var_symbol); break;
			case 2: result =  generateRandomParameterEstimation(); break;
			default : result = generateRandomParameterEstimation();
		}
		return result;
	}
	String getDecisionName (){
		if (decision_counter == 0){
			int rand = getRandom(0,decisionNames_.size()-1);
			return decisionNames_.get(rand);
		}else{
			String decisionName = "\"D_" + decision_counter-- + "\"";
			decisionNames_.add(decisionName);
			return decisionName;
		}
	}
	int getDecisionID(String decisionName){
		String ID = decisionName.split("[_]")[1];
		String trimmedID = ID.replace("\"", "");
		return Integer.parseInt(trimmedID);
	}
	int getRandom (int min, int max){
		
		int range = max - min + 1;
		int num =  rn.nextInt(range) + min;
		return num;
	}
	String getRandomOperator (){
		String result = "+";
		int rand = getRandom(0,3);
		switch (rand){
			case 0 : result= "+"; break;
			case 1 : result= "-"; break;
			case 2 : result= "/"; break;
			case 3 : result= "*"; break;
			default : return "*";
		}
		return result;
	}

	void generateObjectiveModel (String var_symbol){
		while (nbr_decisions > 0 || min_nbr_variables > 0)	{	
			String left_expr = var_symbol + loopCounter;
			// added this section to avoid the left_expr overriding similar variables previously obtained from getExpression method
			// e.g.  A new X3 overriding an existing X3 that may be AND, OR or Param Estimation.
			if (!modelVariables_.containsKey(left_expr)){
				String right_expr = getExpression(loopCounter, var_symbol, left_expr );
				variableDefinition_.put(left_expr, "and_def");
			}
			loopCounter++;
		}
	}
	String generateRandomORRef(String var_symbol){
		
		String or_ref = var_symbol + (++var_index);
		variableDefinition_.put(or_ref, "or_def");
		String decisionName =  getDecisionName();
		String X_def = "decision(" + decisionName + "){\n";
		int nbr_random_option = getRandom(min_nbr_options,max_nbr_options);
		for (int i =0; i < nbr_random_option; i++){
			X_def += "\t \"O" + getDecisionID(decisionName)+ "_" + i +"\" : "+ generateRandomParameterEstimation()+ " \n";
		}
		X_def += "}";
		nbr_decisions--;
		min_nbr_variables--;
		orRefModelVariables_.put(or_ref, X_def);
		return or_ref + ";";
	}
	String generateRandomAndRef(String var_symbol){
		String arith_def_left =  var_symbol + (++var_index);
		variableDefinition_.put(arith_def_left, "param_def");
		min_nbr_variables--;
		
		String arith_def_right =  var_symbol + (++var_index);
		variableDefinition_.put(arith_def_right, "param_def");
		min_nbr_variables--;
		
		orRefModelVariables_.put(arith_def_left, generateRandomParameterEstimation());
		orRefModelVariables_.put(arith_def_right, generateRandomParameterEstimation());

		return arith_def_left + " " + getRandomOperator() + " " + arith_def_right + ";";
	}
	String generateRandomParameterEstimation (){	
		String result = "normalCI(" + getRandom(0,3) + "," + getRandom(4,10) + ");"; 
		int rand =  getRandom(0,2);
		switch (rand){
			case 0: result =  "normalCI(" + getRandom(0,5) + "," + getRandom(6,10) + ");"; break;
			case 1: result =  "normal(" + getRandom(10,20) + "," + getRandom(21,30) + ");"; break;
			case 2: result =  "triangular(" + getRandom(50,100) + "," + getRandom(101,150) + "," + getRandom(151,200) + ");"; break;
			default : result = "normal(" + getRandom(0,5) + "," + getRandom(5,10) + ");";
		}
		return result;	
	}
	
	String generateRadarModel (String modelType){
		
		// determine the minimum number of variables for each objective
		int obj_min_nbr_var =0;
		int obj_decision =0;
		obj_min_nbr_var = min_nbr_variables/nbr_objectives;
		obj_decision = nbr_decisions/nbr_objectives;
		decision_counter = nbr_decisions;
		if (obj_decision <= 0){
			obj_decision = nbr_decisions;
		}
		// added this condition to cater for when the nbr of decisions cannot be divided equally amomg the objectives
		if (nbr_decisions > nbr_objectives && nbr_decisions%nbr_objectives > 0){
			obj_decision += nbr_decisions%nbr_objectives;
		}
		modelType_ = modelType;
		
		StringBuilder model =  new StringBuilder();
		model.append("Model " + modelName_ + "; \n" );
		for (int i =0; i < nbr_objectives; i ++){
			model.append(generateObjectiveDefinition(i) + "\n");
			min_nbr_variables = obj_min_nbr_var;
			nbr_decisions = obj_decision;
			loopCounter =0;
			var_index =0;
			generateObjectiveModel(var_symbols[i]);
			// now loop through variable definition and check those yet to have expr from "model variable",  then
			for (Map.Entry<String, String> entry: variableDefinition_.entrySet() ){
				if (!modelVariables_.containsKey(entry.getKey())){
					String expr = generateRandomParameterEstimation();
					modelVariables_.put(entry.getKey(), expr);
				}
			}
			model.append("");
		}
		
		
		for (Map.Entry<String, String> entry: modelVariables_.entrySet() ){
			model.append(entry.getKey() + " = " +  entry.getValue() + "\n");
		}
		return model.toString();
	}
	String generateObjectiveDefinition(int index){
		String obj_def = "";
		obj_def += "Objective " + opt_dir[getRandom(0,1)] + " OF" +  index + " = " + obj_statistic[getRandom(0,1)] + "(" + var_symbols[index] +"0" + ");";	
		return obj_def;
	}
	public static void main(String[]args) throws Exception {
		try{
			SyntheticModelGenerator smg = new SyntheticModelGenerator();
	    	InputValidator.validateOutputPath(args[0]);
	    	String model_Settings_Path =  args[0];
	    	Config conf =  new Config();
	    	String outputPath = conf.getConfig(model_Settings_Path, "OUTPUT_PATH") ;
	    	if (outputPath.trim().charAt(outputPath.length()-1) != '/'){
	    		outputPath= outputPath.trim() +"/";
			}
	    	InputValidator.validateOutputPath(smg.outputPath);
	    	smg.outputPath = outputPath;
	    	String modelType = "simple";
	    	smg.modelName_ = conf.getConfig(model_Settings_Path, "MODELNAME", "SyntheticModel");
	    	smg.nbr_objectives = Integer.parseInt(conf.getConfig(model_Settings_Path, "NUMBER_OF_OBJECTIVES", "2"));
	    	smg.nbr_decisions = Integer.parseInt(conf.getConfig(model_Settings_Path, "NUMBER_OF_DECISION", "2"));
	    	smg.max_nbr_options = Integer.parseInt(conf.getConfig(model_Settings_Path, "MAX_OPTION_PER_DECISION", "3"));
	    	smg.min_nbr_options = Integer.parseInt(conf.getConfig(model_Settings_Path, "MIN_OPTION_PER_DECISION", "2"));
	    	smg.min_nbr_variables = Integer.parseInt(conf.getConfig(model_Settings_Path, "MIN_NUMBER_OF_VARIABLES", "5"));
	    	String hasDependency = conf.getConfig(model_Settings_Path, "HAS_DEPENDENCY", "TRUE");
	    	
	    	if (hasDependency.equals("TRUE")){modelType =  "complex";}
	    	else{ modelType = "simple";}
	    	String syntheticModel = smg.generateRadarModel(modelType);
	    	Helper.printResults (smg.outputPath , syntheticModel, smg.modelName_  +".rdr", false);
	    	System.out.print("Generated the synthetic model.\nCheck the synthetic model in the following path: " + smg.outputPath);
	    	System.exit(0);
		}
		catch(Exception e){
			System.out.print(e.getMessage());
		}
	}	    
	public String generate(String modelType){
		return this.generateRadarModel(modelType);
	}
	public void setMaxNbrOptions(int max_nbr_options){
		this.max_nbr_options = max_nbr_options;
	}
	public void setMinNbrOptions(int min_nbr_options){
		this.min_nbr_options = min_nbr_options;
	}
	public void setMinNbrVariables(int min_nbr_variables){
		this.min_nbr_variables = min_nbr_variables;
	}
	public void setNbrDecisions(int nbr_decisions){
		this.nbr_decisions = nbr_decisions;
	}
	public void setNbrObjectives(int nbr_objectives){
		this.nbr_objectives = nbr_objectives;
	}
	public void setModelName(String modelName){
		modelName_ = modelName;
	}

	
}
