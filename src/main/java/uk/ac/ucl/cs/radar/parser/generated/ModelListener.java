// Generated from Model.g4 by ANTLR 4.5.1
package uk.ac.ucl.cs.radar.parser.generated;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ModelParser}.
 */
public interface ModelListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ModelParser#model}.
	 * @param ctx the parse tree
	 */
	void enterModel(ModelParser.ModelContext ctx);
	/**
	 * Exit a parse tree produced by {@link ModelParser#model}.
	 * @param ctx the parse tree
	 */
	void exitModel(ModelParser.ModelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code modelObjectiveList}
	 * labeled alternative in {@link ModelParser#model_element}.
	 * @param ctx the parse tree
	 */
	void enterModelObjectiveList(ModelParser.ModelObjectiveListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code modelObjectiveList}
	 * labeled alternative in {@link ModelParser#model_element}.
	 * @param ctx the parse tree
	 */
	void exitModelObjectiveList(ModelParser.ModelObjectiveListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code modelQualityVariableList}
	 * labeled alternative in {@link ModelParser#model_element}.
	 * @param ctx the parse tree
	 */
	void enterModelQualityVariableList(ModelParser.ModelQualityVariableListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code modelQualityVariableList}
	 * labeled alternative in {@link ModelParser#model_element}.
	 * @param ctx the parse tree
	 */
	void exitModelQualityVariableList(ModelParser.ModelQualityVariableListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ModelParser#objective_decl}.
	 * @param ctx the parse tree
	 */
	void enterObjective_decl(ModelParser.Objective_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link ModelParser#objective_decl}.
	 * @param ctx the parse tree
	 */
	void exitObjective_decl(ModelParser.Objective_declContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectiveExpectation}
	 * labeled alternative in {@link ModelParser#objective_def}.
	 * @param ctx the parse tree
	 */
	void enterObjectiveExpectation(ModelParser.ObjectiveExpectationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectiveExpectation}
	 * labeled alternative in {@link ModelParser#objective_def}.
	 * @param ctx the parse tree
	 */
	void exitObjectiveExpectation(ModelParser.ObjectiveExpectationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectiveBooleanProbability}
	 * labeled alternative in {@link ModelParser#objective_def}.
	 * @param ctx the parse tree
	 */
	void enterObjectiveBooleanProbability(ModelParser.ObjectiveBooleanProbabilityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectiveBooleanProbability}
	 * labeled alternative in {@link ModelParser#objective_def}.
	 * @param ctx the parse tree
	 */
	void exitObjectiveBooleanProbability(ModelParser.ObjectiveBooleanProbabilityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectiveProbability}
	 * labeled alternative in {@link ModelParser#objective_def}.
	 * @param ctx the parse tree
	 */
	void enterObjectiveProbability(ModelParser.ObjectiveProbabilityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectiveProbability}
	 * labeled alternative in {@link ModelParser#objective_def}.
	 * @param ctx the parse tree
	 */
	void exitObjectiveProbability(ModelParser.ObjectiveProbabilityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectivePercentile}
	 * labeled alternative in {@link ModelParser#objective_def}.
	 * @param ctx the parse tree
	 */
	void enterObjectivePercentile(ModelParser.ObjectivePercentileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectivePercentile}
	 * labeled alternative in {@link ModelParser#objective_def}.
	 * @param ctx the parse tree
	 */
	void exitObjectivePercentile(ModelParser.ObjectivePercentileContext ctx);
	/**
	 * Enter a parse tree produced by {@link ModelParser#quality_var_decl}.
	 * @param ctx the parse tree
	 */
	void enterQuality_var_decl(ModelParser.Quality_var_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link ModelParser#quality_var_decl}.
	 * @param ctx the parse tree
	 */
	void exitQuality_var_decl(ModelParser.Quality_var_declContext ctx);
	/**
	 * Enter a parse tree produced by the {@code qualityVariableDecision}
	 * labeled alternative in {@link ModelParser#quality_var_def}.
	 * @param ctx the parse tree
	 */
	void enterQualityVariableDecision(ModelParser.QualityVariableDecisionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code qualityVariableDecision}
	 * labeled alternative in {@link ModelParser#quality_var_def}.
	 * @param ctx the parse tree
	 */
	void exitQualityVariableDecision(ModelParser.QualityVariableDecisionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code qualityVariableArithmetic}
	 * labeled alternative in {@link ModelParser#quality_var_def}.
	 * @param ctx the parse tree
	 */
	void enterQualityVariableArithmetic(ModelParser.QualityVariableArithmeticContext ctx);
	/**
	 * Exit a parse tree produced by the {@code qualityVariableArithmetic}
	 * labeled alternative in {@link ModelParser#quality_var_def}.
	 * @param ctx the parse tree
	 */
	void exitQualityVariableArithmetic(ModelParser.QualityVariableArithmeticContext ctx);
	/**
	 * Enter a parse tree produced by the {@code qualityVariableParameter}
	 * labeled alternative in {@link ModelParser#quality_var_def}.
	 * @param ctx the parse tree
	 */
	void enterQualityVariableParameter(ModelParser.QualityVariableParameterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code qualityVariableParameter}
	 * labeled alternative in {@link ModelParser#quality_var_def}.
	 * @param ctx the parse tree
	 */
	void exitQualityVariableParameter(ModelParser.QualityVariableParameterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decisionXOR}
	 * labeled alternative in {@link ModelParser#decision_def}.
	 * @param ctx the parse tree
	 */
	void enterDecisionXOR(ModelParser.DecisionXORContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decisionXOR}
	 * labeled alternative in {@link ModelParser#decision_def}.
	 * @param ctx the parse tree
	 */
	void exitDecisionXOR(ModelParser.DecisionXORContext ctx);
	/**
	 * Enter a parse tree produced by {@link ModelParser#decision_body}.
	 * @param ctx the parse tree
	 */
	void enterDecision_body(ModelParser.Decision_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ModelParser#decision_body}.
	 * @param ctx the parse tree
	 */
	void exitDecision_body(ModelParser.Decision_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ModelParser#option_name}.
	 * @param ctx the parse tree
	 */
	void enterOption_name(ModelParser.Option_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ModelParser#option_name}.
	 * @param ctx the parse tree
	 */
	void exitOption_name(ModelParser.Option_nameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code optionExpression}
	 * labeled alternative in {@link ModelParser#option_def}.
	 * @param ctx the parse tree
	 */
	void enterOptionExpression(ModelParser.OptionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code optionExpression}
	 * labeled alternative in {@link ModelParser#option_def}.
	 * @param ctx the parse tree
	 */
	void exitOptionExpression(ModelParser.OptionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code optionParameter}
	 * labeled alternative in {@link ModelParser#option_def}.
	 * @param ctx the parse tree
	 */
	void enterOptionParameter(ModelParser.OptionParameterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code optionParameter}
	 * labeled alternative in {@link ModelParser#option_def}.
	 * @param ctx the parse tree
	 */
	void exitOptionParameter(ModelParser.OptionParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link ModelParser#parameter_def}.
	 * @param ctx the parse tree
	 */
	void enterParameter_def(ModelParser.Parameter_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link ModelParser#parameter_def}.
	 * @param ctx the parse tree
	 */
	void exitParameter_def(ModelParser.Parameter_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link ModelParser#distribution}.
	 * @param ctx the parse tree
	 */
	void enterDistribution(ModelParser.DistributionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ModelParser#distribution}.
	 * @param ctx the parse tree
	 */
	void exitDistribution(ModelParser.DistributionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ModelParser#distribution_args}.
	 * @param ctx the parse tree
	 */
	void enterDistribution_args(ModelParser.Distribution_argsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ModelParser#distribution_args}.
	 * @param ctx the parse tree
	 */
	void exitDistribution_args(ModelParser.Distribution_argsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ModelParser#distribution_arg}.
	 * @param ctx the parse tree
	 */
	void enterDistribution_arg(ModelParser.Distribution_argContext ctx);
	/**
	 * Exit a parse tree produced by {@link ModelParser#distribution_arg}.
	 * @param ctx the parse tree
	 */
	void exitDistribution_arg(ModelParser.Distribution_argContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprNot}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterExprNot(ModelParser.ExprNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprNot}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitExprNot(ModelParser.ExprNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprDiv}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterExprDiv(ModelParser.ExprDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprDiv}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitExprDiv(ModelParser.ExprDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprIdentifier}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterExprIdentifier(ModelParser.ExprIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprIdentifier}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitExprIdentifier(ModelParser.ExprIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprMult}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterExprMult(ModelParser.ExprMultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprMult}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitExprMult(ModelParser.ExprMultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprNumber}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterExprNumber(ModelParser.ExprNumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprNumber}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitExprNumber(ModelParser.ExprNumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprCompare}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterExprCompare(ModelParser.ExprCompareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprCompare}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitExprCompare(ModelParser.ExprCompareContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprPercent}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterExprPercent(ModelParser.ExprPercentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprPercent}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitExprPercent(ModelParser.ExprPercentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprSub}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterExprSub(ModelParser.ExprSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprSub}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitExprSub(ModelParser.ExprSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprOr}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterExprOr(ModelParser.ExprOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprOr}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitExprOr(ModelParser.ExprOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprPreOperator}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterExprPreOperator(ModelParser.ExprPreOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprPreOperator}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitExprPreOperator(ModelParser.ExprPreOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprBracket}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterExprBracket(ModelParser.ExprBracketContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprBracket}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitExprBracket(ModelParser.ExprBracketContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprAdd}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAdd(ModelParser.ExprAddContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprAdd}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAdd(ModelParser.ExprAddContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprAnd}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAnd(ModelParser.ExprAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprAnd}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAnd(ModelParser.ExprAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprPower}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterExprPower(ModelParser.ExprPowerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprPower}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitExprPower(ModelParser.ExprPowerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compareVariables}
	 * labeled alternative in {@link ModelParser#comparision}.
	 * @param ctx the parse tree
	 */
	void enterCompareVariables(ModelParser.CompareVariablesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compareVariables}
	 * labeled alternative in {@link ModelParser#comparision}.
	 * @param ctx the parse tree
	 */
	void exitCompareVariables(ModelParser.CompareVariablesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compareExpression}
	 * labeled alternative in {@link ModelParser#comparision}.
	 * @param ctx the parse tree
	 */
	void enterCompareExpression(ModelParser.CompareExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compareExpression}
	 * labeled alternative in {@link ModelParser#comparision}.
	 * @param ctx the parse tree
	 */
	void exitCompareExpression(ModelParser.CompareExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomicInteger}
	 * labeled alternative in {@link ModelParser#number}.
	 * @param ctx the parse tree
	 */
	void enterAtomicInteger(ModelParser.AtomicIntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomicInteger}
	 * labeled alternative in {@link ModelParser#number}.
	 * @param ctx the parse tree
	 */
	void exitAtomicInteger(ModelParser.AtomicIntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomicFloat}
	 * labeled alternative in {@link ModelParser#number}.
	 * @param ctx the parse tree
	 */
	void enterAtomicFloat(ModelParser.AtomicFloatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomicFloat}
	 * labeled alternative in {@link ModelParser#number}.
	 * @param ctx the parse tree
	 */
	void exitAtomicFloat(ModelParser.AtomicFloatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomicDecimal}
	 * labeled alternative in {@link ModelParser#number}.
	 * @param ctx the parse tree
	 */
	void enterAtomicDecimal(ModelParser.AtomicDecimalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomicDecimal}
	 * labeled alternative in {@link ModelParser#number}.
	 * @param ctx the parse tree
	 */
	void exitAtomicDecimal(ModelParser.AtomicDecimalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ModelParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteral(ModelParser.IntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ModelParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteral(ModelParser.IntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ModelParser#optimisationDirection}.
	 * @param ctx the parse tree
	 */
	void enterOptimisationDirection(ModelParser.OptimisationDirectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ModelParser#optimisationDirection}.
	 * @param ctx the parse tree
	 */
	void exitOptimisationDirection(ModelParser.OptimisationDirectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ModelParser#comparator}.
	 * @param ctx the parse tree
	 */
	void enterComparator(ModelParser.ComparatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ModelParser#comparator}.
	 * @param ctx the parse tree
	 */
	void exitComparator(ModelParser.ComparatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ModelParser#var_name}.
	 * @param ctx the parse tree
	 */
	void enterVar_name(ModelParser.Var_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ModelParser#var_name}.
	 * @param ctx the parse tree
	 */
	void exitVar_name(ModelParser.Var_nameContext ctx);
}