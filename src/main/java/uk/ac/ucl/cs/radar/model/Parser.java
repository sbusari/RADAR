package uk.ac.ucl.cs.radar.model;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.tree.ParseTree;

import uk.ac.ucl.cs.radar.parser.engine.Visitor;
import uk.ac.ucl.cs.radar.parser.error.handler.ModelExceptionListener;
import uk.ac.ucl.cs.radar.parser.error.handler.ModelParserErrorStrategy;
import uk.ac.ucl.cs.radar.parser.error.handler.UnderlineModelExceptionListener;
import uk.ac.ucl.cs.radar.parser.generated.ModelLexer;
import uk.ac.ucl.cs.radar.parser.generated.ModelParser;
import uk.ac.ucl.cs.radar.utilities.Helper;
/**
 * @author Saheed A. Busari and Emmanuel Letier
 */
public class Parser {
	
	Model semanticModel;
	int nbr_simulation;
	public Parser(){}
	 /**
	 * Sets the semantic model obtained after parsng the decision model.
	 * @param model the semantic model obtained after parsing.
	 */
	public void setSemanticModel (Model semanticModel){
		this.semanticModel =semanticModel;
	}
	 /**
	 * @return the semantic model obtained after parsing.
	 */
	public Model getSemanticModel(){
		return semanticModel;
	}
	 /**
	 * Sets the number of simulation to be run.
	 * @param nbrSimulation the number of simulation.
	 */
	public void setSimulationRun (int nbrSimulation){
		nbr_simulation =nbrSimulation;
	}
	 /**
	 * @return the number of simulation.
	 */
	public int getSimulationRun(){
		return nbr_simulation;
	}
	/**
	 * Constructs the parser with model, simulation number, information value objective and subgraph objective.
	 * @param modelString decision model to be parsed.
	 * @param nbr_simulation the number of simulation.
	 * @param infoValueObjective the objective to be used in computing information value.
	 * @param subGraphObjective the objective used to restrict the generation of a AND/OR graph to a particular objective.
	 */
	public Parser(String modelString, int nbr_simulation, String infoValueObjective,String subGraphObjective){
		parse(modelString,nbr_simulation, infoValueObjective, subGraphObjective);
	}
	/**
	 * instantiate a visitor and then visits the model parse tree to create a semantic model of the decision problem.
	 * @param inputString decision model to be parsed.
	 * @param nbr_simulation the number of simulation.
	 * @param infoValueObjective the objective to be used in computing information value.
	 * @param subGraphObjective the objective used to restrict the generation of a AND/OR graph to a particular objective.
	 * @return a visitor with generated semantic model.
	 */
	public Visitor runVisitor (String inputString, int nbr_simulation,  String infoValueObjective,String subGraphObjective) {
		Visitor visitor = new Visitor(nbr_simulation, infoValueObjective, subGraphObjective);
		String model = inputString + "\n\n\n";
		//ModelExceptionListener errorListener = null;
		UnderlineModelExceptionListener errorListener = null;
		try{
			ANTLRInputStream input = new ANTLRInputStream(model); 
	        ModelLexer lexer = new ModelLexer(input); 
	        CommonTokenStream tokens = new CommonTokenStream(lexer); 
	        ModelParser parser = new ModelParser(tokens); 
	        parser.removeErrorListeners(); // remove ConsoleErrorListener
	        errorListener = new UnderlineModelExceptionListener(); // ModelExceptionListener();
	        parser.addErrorListener(errorListener);
	        //parser.setErrorHandler(new ModelParserErrorStrategy());
	        ParseTree tree = parser.model(); 
	        if (errorListener.getErrorMsg() != "") {
				throw new RuntimeException(errorListener.getErrorMsg() + "\nPossible resolution hints:\n1. Remove extraneous and unrecognised tokens within expressions. \n2. Ensure model keywords are written according to RADAR syntax. \n3. The equality signs '=' must be used when defining a quality variable. \n4 Add semicolon at the end of each statement and ensure quality variable names do not start with a number.");
			}
			visitor.visit(tree);
		}
		catch(InputMismatchException e){
			throw new RuntimeException("Input mismatch error: "+ e.getMessage());
		}
		catch(RuntimeException e){
			if (errorListener.getErrorMsg() != "") {
				throw new RuntimeException(errorListener.getErrorMsg() + "\nPossible resolution hints:\n1. Remove extraneous and unrecognised tokens within expressions. \n2. Ensure model keywords are written according to RADAR syntax. \n3. The equality signs '=' must be used when defining a quality variable. \n4 Add semicolon at the end of each statement and ensure quality variable names do not start with a number. ");
			}else{
				throw new RuntimeException(e.getMessage());
			}
		}
		return visitor;
	}
	/**
	 * parse the decision model and generate the semantic model using a visitor .
	 * @param inputString decision model to be parsed.
	 * @param nbr_simulation the number of simulation.
	 * @param infoValueObjective the objective to be used in computing information value.
	 * @param subGraphObjective the objective used to restrict the generation of a AND/OR graph to a particular objective.
	 */
	void parse(String inputString, int nbr_simulation,  String infoValueObjective,String subGraphObjective)  {
		Visitor visitor = runVisitor (inputString,nbr_simulation, infoValueObjective, subGraphObjective);	
		Model semanticModel = visitor.getSemanticModel();
		setSemanticModel(semanticModel);
	}
	/**
	 * @param modelPath path where the decision model is stored.
	 * @param nbr_simulation the number of simulation.
	 * @param infoValueObjective the objective to be used in computing information value.
	 * @param subGraphObjective the objective used to restrict the generation of a AND/OR graph to a particular objective.
	 * @return a semantic model obtained after parsing.
	 */
	public Model parseCommandLineModel (String modelPath,int nbr_simulation, String infoValueObjective, String subGraphObjective){
		Model semanticModel = null;
		try {
			String model = Helper.readFile(modelPath);
			Parser parser  = new Parser(model,nbr_simulation,infoValueObjective,subGraphObjective );
			semanticModel = parser.getSemanticModel();
		}
		catch (RuntimeException re){
			throw new RuntimeException( "Error: "+ re.getMessage());
		}
		
		return semanticModel;
	}
	public Model parseModel (String readModel,int nbr_simulation, String infoValueObjective, String subGraphObjective){
		Model semanticModel = null;
		try {
			Parser parser  = new Parser(readModel,nbr_simulation,infoValueObjective,subGraphObjective );
			semanticModel = parser.getSemanticModel();
		}
		catch (RuntimeException re){
			throw new RuntimeException( "Error: "+ re.getMessage());
		}

		return semanticModel;
	}
	/**
	 * @param model decision model to be parsed.
	 * @param nbr_simulation the number of simulation.
	 * @param infoValueObjective the objective to be used in computing information value.
	 * @param subGraphObjective the objective used to restrict the generation of a AND/OR graph to a particular objective.
	 * @return a semantic model obtained after parsing.
	 */
	public Model parseUIModel (String model,int nbr_simulation, String infoValueObjective, String subGraphObjective){
		Model semanticModel = null;
		try {
			Parser parser  = new Parser(model,nbr_simulation,infoValueObjective,subGraphObjective );
			semanticModel = parser.getSemanticModel();
		}
		catch (RuntimeException re){
			throw new RuntimeException(re.getMessage());
		}
		return semanticModel;
	}
}
