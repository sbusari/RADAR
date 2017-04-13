package uk.ac.ucl.cs.radar.utilities;

/**
 * @author INTEGRALSABIOLA
 *
 */
public class ConfigSetting {

	public static String MODEL_BEGIN_SYMBOL = "```";
	public static String MODEL_END_SYMBOL = "```";
	public static int NUMBER_OF_ALGORITHMS =6;
	public static String KIND_OF_PROBLEM="DesignOptimisation";
	public static String MKT= "/subjectmodels/MKT.gm";
	public static String EXPERIMENT_NAME= "NEW EXPERIMENT";
	public static String MODEL_INDICES= "ERAM=1,PCFDM=2,NASAM=3,BSPDM=4";
	public static String DEFAULT_EXACT_ALGORITHM= "ExhaustiveSearch";
	public static boolean USE_DEFAULT_PARAMETER_SETTINGS= true;
	public static String APROXIMATE_ALGORITHM_LIST= "NSGAII,SPEA2,IBEA";
	public static int NUMBER_OF_SIMULATION=10000;
	public static double MUTATION= 0.05;
	public static int THREADS=1;
	public static String Emergency_Response_System="ERAM";
	public static long SEED= 0; // 20140604; //
	public static String PCFDM_3OBJ= "/uk.ac.ucl.cs.subjectmodels/PCFDM_FULL.gm";
	//public static int MAX_EVALUATIONS=4;
	public static int MAX_EVALUATIONS=1000;
	public static String Fraud_Detection_System= "PCFDM";
	public static String MARKET_SYSTEM= "MKT";
	public static String NASA_System= "NASAM";
	public static boolean RUN_ALL_APPROX_ALG= true;
	public static String PCFDM_DECISIONSUBSET= "/uk.ac.ucl.cs.subjectmodels/PCFDM_FULL.gm";
	public static String X_Ray_Cargo_System= "XCSM";
	public static String Building_Security_Policy_System ="BSPDM";
	public static String DECISION_MODEL_PATH= "/uk.ac.ucl.cs.subjectmodels/PCFDM_FULL.gm";
	public static String OPTIMISATION_TYPE ="BOTH";
	public static String SUBJECT_SYSTEMS= "--Select--,Emergency Response System,Fraud Detection System,NASA System,Building Policy System";
	public static String XCSM="/uk.ac.ucl.cs.subjectmodels/PCFDM_FULL.gm";
	public static int ALGORITHM_RUNS=3;
	//public static int ALGORITHM_RUNS=3;
	public static String DEFAULT_APRROX_ALGORITHM= "NSGAII";
	public static int POPULATION_SIZE=4;
	public static double  CROSSOVER=0.8;
	
	public static String OUTPUT_DIRECTORY= "/Users/INTEGRALSABIOLA/Downloads/Thesis/";
	public static String ROOTDIRECTORY= "/Users/INTEGRALSABIOLA/Documents/JavaProject/RADAR";
	public static String NASAM ="/uk.ac.ucl.cs.examples/ECS.rdr";
	public static String ERAM ="/uk.ac.ucl.cs.examples/SAS.rdr";
	public static String BSPDM ="/uk.ac.ucl.cs.examples/BSPDM.rdr";
	public static String PCFDM= "/uk.ac.ucl.cs.examples/PCFDM_FULL.rdr";
	public static String CBA= "/uk.ac.ucl.cs.examples/CBA.rdr";
	
	/*public static String OUTPUT_DIRECTORY = "/home/sbusari/Thesis/";
	public static String ROOTDIRECTORY = "/home/sbusari/Thesis";
	public static String NASAM ="/experiment/ECS.gm";
	public static String ERAM ="/experiment/SAS.gm";
	public static String BSPDM ="/experiment/BSPDM.gm";
	public static String PCFDM= "/experiment/PCFDM_FULL.gm";*/

	
}
