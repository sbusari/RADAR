package uk.ac.ucl.cs.radar.utilities;

/**
 * @author INTEGRALSABIOLA
 *
 */
public class ConfigSetting {

	//Tool Specific Configs
	public static String MACEXE = "/usr/local/bin/dot";
	public static String WINEXE = "c:/Program Files (x86)/Graphviz 2.28/bin/dot.exe";
	public static String LINUXEXE = "/usr/bin/dot";
	public static String EDITORTAB = "Editor";
	public static String CONSOLETAB = "Console";
	public static String TABANALYSISRESULT = "Analysis Result";
	public static String TABANALYSISSETTINGS= "Analysis Settings";
	public static String TABDECISIONS= "Decisions";
	public static String TABDECISIONGRAPH= "Decision Graph";
	public static String TABGOALGRAPH= "Goal Graph";
	public static String TABPARETO= "Pareto Front";
	public static String TABINFOVALUEANALYSIS= "Information Value Analysis";
	public static String TABOPTIMISATIONANALYSIS= "Optimisation Analysis";
	
	public static String INFOVALUESUBGRAPHOBJECTIVE_CBA = "ENB"; 
	public static String INFOVALUESUBGRAPHOBJECTIVE_FDM = "FraudDetectionBenefit"; 
	public static String INFOVALUESUBGRAPHOBJECTIVE_BSPDM = "ExpectedUtility"; 
	public static String INFOVALUESUBGRAPHOBJECTIVE_SAS = "ExpectedCostOfDisclosures"; 
	public static String INFOVALUESUBGRAPHOBJECTIVE_ECM = "ExpectedCostOfDisclosures"; 
	// Model based Configs
	public static String ECM ="ECS";
	public static String SAS ="SAS";
	public static String BSPDM ="BSPDM";
	public static String FDM= "FDM";
	public static String CBA= "CBA";
	//Algorithm based Configs
	public static int NUMBER_OF_ALGORITHMS =6;
	public static String DEFAULT_EXACT_ALGORITHM= "ExhaustiveSearch";
	public static boolean USE_DEFAULT_PARAMETER_SETTINGS= true;
	public static String APROXIMATE_ALGORITHM_LIST= "NSGAII,SPEA2,IBEA";
	public static int NUMBER_OF_SIMULATION=10000;
	public static double MUTATION= 0.05;
	public static int THREADS=1;
	public static long SEED= 0; // 20140604; //
	public static int MAX_EVALUATIONS=1000;
	public static int ALGORITHM_RUNS=3;
	public static String DEFAULT_APRROX_ALGORITHM= "NSGAII";
	public static int POPULATION_SIZE=4;
	public static double  CROSSOVER=0.8;
	public static String OUTPUT_DIRECTORY= "/Users/INTEGRALSABIOLA/Downloads/Thesis/";
	public static String ROOTDIRECTORY= "/Users/INTEGRALSABIOLA/Documents/JavaProject/RADAR";


	
}
