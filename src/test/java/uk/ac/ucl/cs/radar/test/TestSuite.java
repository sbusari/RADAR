package uk.ac.ucl.cs.radar.test;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.ac.ucl.cs.radar.model.*;
import uk.ac.ucl.cs.radar.parser.error.handler.UnderlineModelExceptionListener;
import uk.ac.ucl.cs.radar.parser.generated.ModelLexer;
import uk.ac.ucl.cs.radar.parser.generated.ModelParser;
import uk.ac.ucl.cs.radar.utilities.Helper;

public class TestSuite {
	
	String modelString ="";
	ANTLRInputStream input = null;
	ModelLexer lexer = null;
	ParseTree tree = null;
	ModelParser parser = null;
	Model semantic_model =null;
	Map<String,String> inputFiles;
	Map<String,String> modelStrings;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	@Before
	public void setUp() throws Exception {
		inputFiles = new HashMap<String,String>();
		inputFiles.put("ENB","./src/main/resources/uk.ac.ucl.cs.examples/CBA/CBA.rdr");
		//inputFiles.put("FraudDetectionBenefit", "./src/main/resources/uk.ac.ucl.cs.examples/FDM/FDM.rdr"); 
		//inputFiles.put("ENB","./src/main/resources/uk.ac.ucl.cs.examples/SAS/SAS.rdr"); 
		//inputFiles.put("ExpectedUtility","./src/main/resources/uk.ac.ucl.cs.examples/ECS/ECS.rdr"); 
		inputFiles.put("ExpectedCostOfDisclosures", "./src/main/resources/uk.ac.ucl.cs.examples/BSPDM/BSPDM.rdr");  
		
		modelStrings = new HashMap<String,String>();
		for (Map.Entry<String, String> entry: inputFiles.entrySet()){
			modelString = Helper.readFile(entry.getValue());
	        input = new ANTLRInputStream(modelString);
	        lexer = new ModelLexer(input);
	        CommonTokenStream tokens = new CommonTokenStream(lexer);
	        parser = new ModelParser(tokens);
	        parser.removeErrorListeners();
	        parser.addErrorListener(new UnderlineModelExceptionListener()); 
			modelStrings.put(entry.getKey(),modelString);
		}
        
	}

	@After
	public void tearDown() throws Exception {
		
	}
	@Test
	public void semanticModelNotNull() {
		
		for (Map.Entry<String, String> entry: modelStrings.entrySet()){
			Parser parser = new Parser (entry.getValue(),10000, "",""); 
			Model semantic_model = parser.getSemanticModel();
			assertNotNull(semantic_model);
		}
		
	}
	@Test
	public void solutionSpace() {
		for (Map.Entry<String, String> entry: modelStrings.entrySet()){
			Parser parser = new Parser (entry.getValue(),10000, "",""); 
			Model semantic_model = parser.getSemanticModel();
			long solutionSpace = semantic_model.getSolutionSpace();
			assertTrue(solutionSpace > 0);
		}
	}
	@Test
	public void evaluateSolutions() {
		for (Map.Entry<String, String> entry: modelStrings.entrySet()){
			Parser parser = new Parser (entry.getValue(),10000, "",""); 
			Model semantic_model = parser.getSemanticModel();
			List<Solution> solutions = semantic_model.getAllSolutions().list();
			Map<Solution, double[]> evaluatedSolutions = new LinkedHashMap<Solution, double[]>();
			for (Solution s: solutions){
				evaluatedSolutions.put(s, semantic_model.evaluate(semantic_model.getObjectives(), s));	
			}
			assertTrue(evaluatedSolutions.size() > 0);
		}
	}
	@Test
	public void paretoOptimal() {
		for (Map.Entry<String, String> entry: modelStrings.entrySet()){
			Parser parser = new Parser (entry.getValue(),10000, "",""); 
			Model semantic_model = parser.getSemanticModel();
			List<Solution> solutions = semantic_model.getAllSolutions().list();
			Map<Solution, double[]> evaluatedSolutions = new LinkedHashMap<Solution, double[]>();
			for (Solution s: solutions){
				evaluatedSolutions.put(s, semantic_model.evaluate(semantic_model.getObjectives(), s));	
			}
			Map<Solution, double[]> optimalSolutions = new Optimiser().getParetoSet(evaluatedSolutions, semantic_model.getObjectives());
			assertTrue(optimalSolutions.size() > 0);
		}
		
	}
	@Test
	public void informationValueObjectiveNotNull() {
		for (Map.Entry<String, String> entry: modelStrings.entrySet()){
			Parser parser = new Parser (entry.getValue(),10000, "",""); 
			Model semantic_model = parser.getSemanticModel();
			Objective infoValueObjective = semantic_model.getInfoValueObjective();
			assertNull(infoValueObjective);
		}
	}
	@Test
	public void confirmInformationValueObjective() {
		for (Map.Entry<String, String> entry: modelStrings.entrySet()){
			Parser parser = new Parser (entry.getValue(),10000, entry.getKey(),entry.getKey()); 
			Model semantic_model = parser.getSemanticModel();
			Objective infoValueObjective = semantic_model.getInfoValueObjective();
			assertEquals(entry.getKey(),infoValueObjective.getLabel());
		}

	}
	@Test
	public void computeInformationValue() {
		for (Map.Entry<String, String> entry: modelStrings.entrySet()){
			Parser parser = new Parser (entry.getValue(),10000,  entry.getKey(),entry.getKey()); 
			Model semantic_model = parser.getSemanticModel();
			AnalysisResult result = new AnalysisResult(semantic_model.getObjectives(),semantic_model.getDecisions());
			List<Solution> solutions = semantic_model.getAllSolutions().list();
			for (Solution s: solutions){
				result.addEvaluation(s, semantic_model.evaluate(semantic_model.getObjectives(), s));	
			}
			result.addShortlist(new Optimiser().getParetoSet(result.getEvaluatedSolutions(),semantic_model.getObjectives()));
			Objective infoValueObjective = semantic_model.getInfoValueObjective();
			List<String> paramNames = null;
			List<Parameter> parameters = null;
			if (infoValueObjective != null ){
				paramNames = semantic_model.getParameters();
				parameters = Model.getParameterList(paramNames, semantic_model);
				semantic_model.computeInformationValue(result,infoValueObjective, result.getShortListSolutions(), parameters);
			}
			assertNotNull(paramNames);
			assertNotNull(parameters);
			assertNotNull(result.informationValueResultToString());
		}

	}
	@Test
	public void solveModel() {
		for (Map.Entry<String, String> entry: modelStrings.entrySet()){
			Parser parser = new Parser (entry.getValue(),10000, "",""); 
			Model semantic_model = parser.getSemanticModel();
			AnalysisResult result = ModelSolver.solve(semantic_model);
			assertNotNull(result);
		}
		

	}
	@Test
	public void allSolutions() {
		for (Map.Entry<String, String> entry: modelStrings.entrySet()){
			Parser parser = new Parser (entry.getValue(),10000, "",""); 
			Model semantic_model = parser.getSemanticModel();
			List<Solution> solutions = semantic_model.getAllSolutions().list();
			assertTrue(solutions.size() > 0);
		}
	}
	@Test
	public void nonEmptyModelDecision() {
		for (Map.Entry<String, String> entry: modelStrings.entrySet()){
			Parser parser = new Parser (entry.getValue(),10000, "",""); 
			Model semantic_model = parser.getSemanticModel();
			List<Decision> decisions = semantic_model.getDecisions();
			assertTrue( decisions.size() > 0);
		}

	}
	@Test
	public void nonEmptyModelObjectives() {
		for (Map.Entry<String, String> entry: modelStrings.entrySet()){
			Parser parser = new Parser (entry.getValue(),10000, "",""); 
			Model semantic_model = parser.getSemanticModel();
			List<Objective> objectives = semantic_model.getObjectives();
			assertTrue(objectives.size() > 0);
		}

	}
	@Test
	public void nonEmptyModelQualityVariables() {
		for (Map.Entry<String, String> entry: modelStrings.entrySet()){
			Parser parser = new Parser (entry.getValue(),10000, "",""); 
			Model semantic_model = parser.getSemanticModel();
			List<QualityVariable> qualityVariables = new ArrayList<QualityVariable>(semantic_model.getQualityVariables().values());
			assertTrue(qualityVariables.size() > 0);
		}
	}
	@Test
	public void equalEmptySolutions() {
		// testing equals works on empty solutions
		Solution s1 = new Solution();
		Solution s2 = new Solution();
		assertEquals( s1, s2);
	}
	
	@Test
	public void equalOnSingleSolutionWithOneDecision() {
		// testing equals works on solution with single decision
		List<String> options = new ArrayList<String>();
		options.add("X");
		options.add("Y");
		Decision d = new Decision();
		d.setDecisionLabel("D");
		d.setOptions(options);

		Map<Decision, String> m3 = new LinkedHashMap<Decision, String>();
		m3.put(d, "X");
		Solution s3 = new Solution(m3);

		Map<Decision, String> m4 = new LinkedHashMap<Decision, String>();
		m4.put(d, "X");
		Solution s4 = new Solution(m4);
		assertEquals( s3,s4);
	}
	@Test
	public void idempotence() {
		// Testing idempotence of add in SolutionSet:
		// If we add the same solution twice, the set should hold it only once.
		List<String> options = new ArrayList<String>();
		options.add("X");
		options.add("Y");
		Decision d = new Decision();
		d.setDecisionLabel("D");
		d.setOptions(options);

		Map<Decision, String> m3 = new LinkedHashMap<Decision, String>();
		m3.put(d, "X");
		Solution s3 = new Solution(m3);

		Map<Decision, String> m4 = new LinkedHashMap<Decision, String>();
		m4.put(d, "X");
		Solution s4 = new Solution(m4);
		
		SolutionSet slns = new SolutionSet();
		slns.add(s3);
		slns.add(s4);
		assertEquals(slns.size(),1);
	}
	
	

}
