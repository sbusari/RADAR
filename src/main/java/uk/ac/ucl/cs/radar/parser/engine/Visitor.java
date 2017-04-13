package uk.ac.ucl.cs.radar.parser.engine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.antlr.v4.runtime.tree.TerminalNode;

import uk.ac.ucl.cs.radar.model.Decision;
import uk.ac.ucl.cs.radar.model.Model;
import uk.ac.ucl.cs.radar.model.ModelConstructor;
import uk.ac.ucl.cs.radar.model.OR_Refinement;
import uk.ac.ucl.cs.radar.model.Objective;
import uk.ac.ucl.cs.radar.model.QualityVariable;
import uk.ac.ucl.cs.radar.model.Value;
import uk.ac.ucl.cs.radar.parser.generated.ModelBaseVisitor;
import uk.ac.ucl.cs.radar.parser.generated.ModelParser;
import uk.ac.ucl.cs.radar.parser.generated.ModelParser.Model_elementContext;

public class Visitor extends ModelBaseVisitor<Value> {
	
	/**
	 * Stores operators that con used in an 'argument' of a compare expression and a distribution
	 */
	String operator = "\\+|\\-|\\*|\\/|\\^|\\%";
	/**
	 * Stores a boolean value to represent of the argument of a distribution e.g. normal, has an expression that involves arithmetic operators
	 */
	boolean argumentHasExpr;
	/**
	 * Stores the scalar value of the expression in the argument of a distribution or comparison expression
	 */
	String computedArgumentExpression;
	/**
	 * the semantic model that stores all model constucts
	 */
	Model semanticModel;
	/**
	 * Stores all the quality variable in model
	 */
	Map<String, QualityVariable> qv_list;
	/**
	 * Stores the objective definition for each objective
	 */
	Map<String, Value> obj_definitions;
	/**
	 * Stores all the objective in the model
	 */
	Map<String, Objective> obj_list;
	/**
	 * Stores all the decisions in the model
	 */
	Map<String, Decision> decision_list;
	/**
	 * A variable that represents an ANDRef, but only has a name.
	 */
	QualityVariable and_Ref_Parent; 
	/**
	 * A variable that stores the parent of an arithmetic expression
	 */
	QualityVariable arith_expr_Parent;
	/**
	 * Stores the parent of an Identifier. 
	 */
	QualityVariable idParent;
	/**
	 * Added to store a list of quality variable children to be used for checking model acyclicity.
	 */
	String qualityVariableChildren;
	/**
	 * The number of scenarios durin MCS
	 */
	int nbr_simulation;
	/**
	 * Information value objective used to comput evtpi and evppi
	 */
	String infoValueObjective;
	/**
	 * Variable (objective) from which the generation of a goal graph starts
	 */
	String subGraphObjective;
	/**
	 * An instance of a model constructor class used to construct a semantic model from the AST
	 */
	ModelConstructor modelConstructor;
	
	public Visitor(int nbr_sim,  String infoValueObj,String subGraphObj){
		nbr_simulation =nbr_sim;
		infoValueObjective = infoValueObj;
		subGraphObjective = subGraphObj;
	}
	
	public Model getSemanticModel() {
		return semanticModel;
	}
	
	@Override 
	public Value visitModel(ModelParser.ModelContext ctx) {
		modelConstructor = new ModelConstructor (nbr_simulation);
		qv_list = new LinkedHashMap<String,QualityVariable > ();
		obj_list = new LinkedHashMap<String,Objective > ();
		decision_list = new LinkedHashMap<String,Decision>();
		obj_definitions = new LinkedHashMap<String, Value> ();
		semanticModel = modelConstructor.createNewModel();
		if (ctx.model_element() != null && ctx.model_element().size() > 0 ){
			for (Model_elementContext modelElementContext : ctx.model_element()){
				visit (modelElementContext);
			}
		}else{
			throw new RuntimeException ("Model cannot be empty.");
		}
		// need objective definition to get the quality variable an objective refers to
		
		modelConstructor.addObjectivesToModel(semanticModel, obj_definitions, obj_list,qv_list,infoValueObjective,subGraphObjective);
		modelConstructor.addQualityVariablesToModel(semanticModel, qv_list );
		modelConstructor.addDecisionsToModel(semanticModel,decision_list);
		modelConstructor.addModelName(semanticModel,ctx.var_name().getText());
		
		semanticModel.setNbr_Simulation(nbr_simulation);
		semanticModel.setSubgraphVariable(subGraphObjective);
		modelConstructor.setModelParameterLabels(semanticModel);
		return new Value(null);
	}
	
	@Override 
	public Value visitModelObjectiveList(ModelParser.ModelObjectiveListContext ctx) { 
		if (ctx.objective_decl() != null && ctx.objective_decl().size() > 0){
			for(ModelParser.Objective_declContext obj_decl_ctx : ctx.objective_decl()){
				visit(obj_decl_ctx);
			}
		}else{
			throw new RuntimeException ("Model must have objectives.");
		}
		return new Value(null);
	}
	
	@Override 
	public Value visitObjective_decl(ModelParser.Objective_declContext ctx) {
		String obj_name =  ctx.var_name().getText().trim();
		Value obj_def = null;
		if (ctx.objective_def() != null){
			obj_def = visit(ctx.objective_def());
		}else{
			obj_def= modelConstructor.addObjectiveExpectation(obj_name);
		}
		String obj_opt_direction = ctx.optimisationDirection().getText();
		Objective obj = modelConstructor.createNewObjective(obj_name,obj_opt_direction);
		// set model infovalueObj if the objective name equal info value obj
		if (ctx.number() != null){
			Value margin = visit (ctx.number());
			obj.setMargin(margin.convertToDouble());
		}
		obj_definitions.put(obj_name, obj_def);
		obj_list.put(obj_name, obj);
		return new Value(obj);
	}
	
	@Override 
	public Value visitObjectiveExpectation(ModelParser.ObjectiveExpectationContext ctx) {
		String obj_qv =  ctx.var_name().getText().trim();
		Value obj_exp = modelConstructor.addObjectiveExpectation(obj_qv);
		return obj_exp; 
	}
	
	@Override 
	public Value visitObjectiveBooleanProbability(ModelParser.ObjectiveBooleanProbabilityContext ctx) {
		String obj_qv =  ctx.var_name().getText().trim();
		Value obj_bool_prob = modelConstructor.addObjectiveBooleanProbablity(obj_qv);
		return obj_bool_prob; 
	}
	@Override 
	public Value visitObjectiveProbability(ModelParser.ObjectiveProbabilityContext ctx) {
		Value comparisonExpr =  visit(ctx.comparision());
		Value obj_prob = modelConstructor.addObjectiveProbablity(comparisonExpr);
		return obj_prob; 
	}
	
	@Override 
	public Value visitObjectivePercentile(ModelParser.ObjectivePercentileContext ctx) {
		String obj_qv =  ctx.var_name().getText().trim();
		String var = ctx.integerLiteral().getText().trim();
		String sign = "";
		if (ctx.op != null)sign	=ctx.op.getText();
		Value obj_percentile = modelConstructor.addObjectivePercentile(obj_qv, var,sign);
		return obj_percentile; 
	}
	
	public Value visitModelQualityVariableList(ModelParser.ModelQualityVariableListContext ctx) { 
		if (ctx.quality_var_decl() != null && ctx.quality_var_decl().size() > 0){
			for(ModelParser.Quality_var_declContext qv_decl_ctx : ctx.quality_var_decl()){
				visit(qv_decl_ctx);
			}
		}
		return new Value(null);
	}
	
	@Override 
	public Value visitQuality_var_decl(ModelParser.Quality_var_declContext ctx) { 
		QualityVariable qv = modelConstructor.createNewQualityVariable();
		String qv_name = ctx.var_name().getText().trim();
		and_Ref_Parent = qv;
		arith_expr_Parent = qv;
		idParent = qv;
		qualityVariableChildren = "";// set to empty before we visit qv expression.
		Value qv_def = visit(ctx.quality_var_def());
		qv = modelConstructor.addQualityVariableExpression(qv, qv_name,qv_def,arith_expr_Parent, qualityVariableChildren);
		qv_list.put(qv_name.toString(), qv);
		modelConstructor.addQualityVariableParameter(semanticModel,qv);
		and_Ref_Parent = null;
		idParent = null;
		arith_expr_Parent = null;
		qualityVariableChildren = null;
		return new Value (qv);
	}
	
	@Override 
	public Value visitQualityVariableArithmetic(ModelParser.QualityVariableArithmeticContext ctx) {
		return visit (ctx.arithmetic_expr());
	}
	
	@Override 
	public Value visitQualityVariableDecision(ModelParser.QualityVariableDecisionContext ctx) {
		return visit (ctx.decision_def());
	}
	
	@Override 
	public Value visitDecisionXOR(ModelParser.DecisionXORContext ctx) {
		return visit(ctx.decision_body());
	}
	
	@Override 
	public Value visitDecision_body(ModelParser.Decision_bodyContext ctx) { 
		OR_Refinement or_ref = modelConstructor.createNewOr_Refinement();
		Decision d = modelConstructor.createNewDecision();
		String decision_name = ctx.decision_Name.getText().replace("\"", "").trim();
		d.setDecisionLabel(decision_name);
		ArrayList<String> optionNames =  new ArrayList<String>();
		for (ModelParser.Option_nameContext optionNameContext : ctx.option_name()){
			String optionName = optionNameContext.getText().replace("\"", "").trim();
			optionNames.add(optionName);
			d.addOption(optionName);
		}
		int i =0;
		for (ModelParser.Option_defContext optionDefContext : ctx.option_def()){
			// could be a unary, binary expr or a parameter.
			Value definition =visit(optionDefContext);
			modelConstructor.addOR_RefinementDefinition(or_ref,optionNames.get(i),definition,and_Ref_Parent,decision_name);
			i++;
		}
		
		or_ref.setDecision(d);
		decision_list.put(decision_name, d);
		return new Value (or_ref);
	}
	
	@Override 
	public Value visitOptionExpression(ModelParser.OptionExpressionContext ctx) { 		
		Value option_param_expr =  visit(ctx.arithmetic_expr());
		return  option_param_expr;
	}
	
	@Override 
	public Value visitOptionParameter(ModelParser.OptionParameterContext ctx) {
		Value option_param_def_expr = visit(ctx.parameter_def());
		
		return  option_param_def_expr;
	}
	
	@Override 
	public Value visitQualityVariableParameter(ModelParser.QualityVariableParameterContext ctx) {
		return visit (ctx.parameter_def());
	}
	
	@Override 
	public Value visitParameter_def(ModelParser.Parameter_defContext ctx) { 
		Value distribution = visit(ctx.distribution());
		List<Value>distributionArgument = new ArrayList<Value>();
		if (ctx.distribution_arg() != null){ 
			for (ModelParser.Distribution_argContext arg : ctx.distribution_arg()){
				distributionArgument.add(visit(arg));
			}
		}
		// model updted so far with QVs only, to be used in checking that expressions within a distribution are valid.
		Model tempModel = new Model();
		modelConstructor.addQualityVariablesToModel(tempModel, qv_list );
		Value param_def_expr =  modelConstructor.addDistribution(distribution, distributionArgument, tempModel) ;
		return param_def_expr;
	}
	
	@Override public Value visitDistribution(ModelParser.DistributionContext ctx) {
		Value exprValue = new Value (ctx.distributionValue.getText().trim().toUpperCase(Locale.ENGLISH));
		return exprValue;
	}

	@Override public Value visitDistribution_arg(ModelParser.Distribution_argContext ctx) {
		
		String distributionArgument = ctx.arithmetic_expr().getText();
		argumentHasExpr = modelConstructor.doesDistributionArgumentHasExpr(distributionArgument, operator);
		Value atomicExpr = visit(ctx.arithmetic_expr());
		if (computedArgumentExpression != null){
			atomicExpr = modelConstructor.updateDistributionArguementValue(computedArgumentExpression);
			computedArgumentExpression = null;
		}
		argumentHasExpr = false;
		return atomicExpr;
	}
	@Override 
	public Value visitVar_name(ModelParser.Var_nameContext ctx) {
		String idValue = ctx.Identifier().getText();
		Value varname = modelConstructor.addIdentifierExpression(idValue,idParent);
		qualityVariableChildren += idValue + ",";
		return varname;
		
	}

	@Override 
	public Value visitExprCompare(ModelParser.ExprCompareContext ctx) {
		Value comparison= visit (ctx.comparision());
		return comparison;
	}
	
	@Override public Value visitCompareVariables(ModelParser.CompareVariablesContext ctx) { 
		Value left_var = visit (ctx.var_name(0));
		Value right_var = visit (ctx.var_name(1));
		String relaionalOperator = ctx.relationalOp.getText().trim();
		Value expValue = modelConstructor.addComparatorExpression (left_var,right_var, relaionalOperator);
		return expValue;
	}
	
	@Override public Value visitCompareExpression(ModelParser.CompareExpressionContext ctx) { 
		Value var = visit (ctx.var_name());
		String comparisonArgument = ctx.arithmetic_expr().getText();
		argumentHasExpr = modelConstructor.doesDistributionArgumentHasExpr(comparisonArgument, operator);
		Value expr =  visit (ctx.arithmetic_expr());
		if (computedArgumentExpression != null){
			expr = modelConstructor.updateDistributionArguementValue(computedArgumentExpression);
			computedArgumentExpression = null;
		}
		argumentHasExpr = false;
		String relaionalOperator = ctx.relationalOp.getText().trim();
		Value expValue = modelConstructor.addComparatorExpression (var,expr, relaionalOperator);
		return expValue; 
	}

	@Override public Value visitExprOr(ModelParser.ExprOrContext ctx) {
		Value leftOperand = visit (ctx.arithmetic_expr(0));
		Value rightOperand =visit (ctx.arithmetic_expr(1));
		String op = ctx.OR().getText();
		Value expValue = modelConstructor.addBinaryExpression (leftOperand,rightOperand, op,arith_expr_Parent);
		return expValue;
	}

	@Override public Value visitExprAnd(ModelParser.ExprAndContext ctx) {
		Value leftOperand = visit (ctx.arithmetic_expr(0));
		Value rightOperand =  visit (ctx.arithmetic_expr(1));
		String op = ctx.AND().getText();
		Value expValue = modelConstructor.addBinaryExpression (leftOperand,rightOperand, op,arith_expr_Parent);
		return expValue;
	}

	@Override public Value visitExprNot(ModelParser.ExprNotContext ctx) {
		Value expr = visit (ctx.arithmetic_expr());
		Value expValue = modelConstructor.addUnaryExpression(expr, "!",arith_expr_Parent);
		return expValue;
	}

	@Override public Value visitExprBracket(ModelParser.ExprBracketContext ctx) { 
		Value exprValue =visit(ctx.arithmetic_expr());
		return exprValue;
	}	
	
	@Override 
	public Value visitExprPreOperator(ModelParser.ExprPreOperatorContext ctx) { 
		
		String op = ctx.op.getText();
		Value expr = visit (ctx.arithmetic_expr());
		Value expValue = modelConstructor.addUnaryExpression(expr, op,arith_expr_Parent);

		return expValue;
	}
	
	@Override public Value visitExprDiv(ModelParser.ExprDivContext ctx) { 
		Value leftOperand = visit (ctx.arithmetic_expr(0));
		Value rightOperand =  visit (ctx.arithmetic_expr(1));
		Value expValue = modelConstructor.addBinaryExpression (leftOperand,rightOperand, "/",arith_expr_Parent);
		if (argumentHasExpr == true){
			Model tempModel = new Model();
			modelConstructor.addQualityVariablesToModel(tempModel, qv_list );
			double leftValue = modelConstructor.convertParameterExpressionsToNumber(leftOperand, tempModel);
			double rightValue = modelConstructor.convertParameterExpressionsToNumber(rightOperand, tempModel);
			computedArgumentExpression = String.valueOf(leftValue / rightValue);
			//computedArgumentExpression = String.valueOf(leftOperand.convertToDouble() / rightOperand.convertToDouble());
		}
		return expValue;
	}
	
	@Override public Value visitExprMult(ModelParser.ExprMultContext ctx) {
		Value leftOperand = visit (ctx.arithmetic_expr(0));
		Value rightOperand =  visit (ctx.arithmetic_expr(1));
		Value expValue = modelConstructor.addBinaryExpression (leftOperand,rightOperand, "*",arith_expr_Parent);
		if (argumentHasExpr == true){
			Model tempModel = new Model();
			modelConstructor.addQualityVariablesToModel(tempModel, qv_list );
			double leftValue = modelConstructor.convertParameterExpressionsToNumber(leftOperand, tempModel);
			double rightValue = modelConstructor.convertParameterExpressionsToNumber(rightOperand, tempModel);
			computedArgumentExpression = String.valueOf(leftValue * rightValue);
			//computedArgumentExpression = String.valueOf(leftOperand.convertToDouble() * rightOperand.convertToDouble());
		}
		return expValue;
	}
	
	@Override public Value visitExprAdd(ModelParser.ExprAddContext ctx) {
		Value leftOperand = visit (ctx.arithmetic_expr(0));
		Value rightOperand =  visit (ctx.arithmetic_expr(1));
		Value expValue = modelConstructor.addBinaryExpression (leftOperand,rightOperand, "+",arith_expr_Parent);
		if (argumentHasExpr == true){
			Model tempModel = new Model();
			modelConstructor.addQualityVariablesToModel(tempModel, qv_list );
			double leftValue = modelConstructor.convertParameterExpressionsToNumber(leftOperand, tempModel);
			double rightValue = modelConstructor.convertParameterExpressionsToNumber(rightOperand, tempModel);
			computedArgumentExpression = String.valueOf(leftValue + rightValue);
			//computedArgumentExpression = String.valueOf(leftOperand.convertToDouble() + rightOperand.convertToDouble());
		}
		return expValue;
	}
	
	@Override public Value visitExprSub(ModelParser.ExprSubContext ctx) {
		Value leftOperand = visit (ctx.arithmetic_expr(0));
		Value rightOperand =  visit (ctx.arithmetic_expr(1));
		Value expValue = modelConstructor.addBinaryExpression (leftOperand,rightOperand, "-",arith_expr_Parent);
		if (argumentHasExpr == true){
			Model tempModel = new Model();
			modelConstructor.addQualityVariablesToModel(tempModel, qv_list );
			double leftValue = modelConstructor.convertParameterExpressionsToNumber(leftOperand, tempModel);
			double rightValue = modelConstructor.convertParameterExpressionsToNumber(rightOperand, tempModel);
			computedArgumentExpression = String.valueOf(leftValue - rightValue);
			//computedArgumentExpression = String.valueOf(leftOperand.convertToDouble() - rightOperand.convertToDouble());
		}
		
		return expValue;
	}
	
	@Override public Value visitExprPower(ModelParser.ExprPowerContext ctx) {
		Double arithmetic_expr = null;
		Value base = visit (ctx.arithmetic_expr(0));
		Value power = visit (ctx.arithmetic_expr(1));
		//======
		Model tempModel = new Model();
		modelConstructor.addQualityVariablesToModel(tempModel, qv_list );
		//=====
		arithmetic_expr= modelConstructor.findExponent(base,power,tempModel);
		Value value = modelConstructor.addNumberExpression(String.valueOf(arithmetic_expr) );
		if (argumentHasExpr == true){
			computedArgumentExpression = arithmetic_expr.toString();
		}
		// power cannot have an ID
		return value;
	}
	
	@Override 
	public Value visitExprPercent(ModelParser.ExprPercentContext ctx) {
		Value number = visit( ctx.arithmetic_expr());
		try {
			Double.parseDouble( number.toString());
		}catch(Exception e){
			throw new RuntimeException ("Incorrect syntax for percentage arithmetic_expr. Ensure you specify a number. \n  Check documentation for details.");
		}
		Double percentValue = number.convertToDouble()/100;
		Value value = modelConstructor.addNumberExpression(String.valueOf(percentValue) );
		return value;
	}
	
	@Override public Value visitExprNumber(ModelParser.ExprNumberContext ctx) {
		Value atomicExpr = visit(ctx.number());
		return atomicExpr;
	}
	
	@Override 
	public Value visitAtomicInteger(ModelParser.AtomicIntegerContext ctx) { 
		String integerValue = ctx.integerLiteral().getText().trim();
		Value value = modelConstructor.addNumberExpression(integerValue);
		return value;  
	}
	
	@Override 
	public Value visitAtomicFloat(ModelParser.AtomicFloatContext ctx) {
		String floatValue =ctx.FloatingPointLiteral().getText().trim();
		Value value = modelConstructor.addNumberExpression(floatValue);
		return value;
	}
	
	@Override 
	public Value visitAtomicDecimal(ModelParser.AtomicDecimalContext ctx) { 
		String decimalValue =ctx.DecimalLiteral().getText().trim();
		Value atomicdecimal = modelConstructor.addNumberExpression(decimalValue);
		return atomicdecimal;
	}

	
}
