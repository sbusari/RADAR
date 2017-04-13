// Generated from Model.g4 by ANTLR 4.5.1
package uk.ac.ucl.cs.radar.parser.generated;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ModelParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ModelVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ModelParser#model}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel(ModelParser.ModelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code modelObjectiveList}
	 * labeled alternative in {@link ModelParser#model_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModelObjectiveList(ModelParser.ModelObjectiveListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code modelQualityVariableList}
	 * labeled alternative in {@link ModelParser#model_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModelQualityVariableList(ModelParser.ModelQualityVariableListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#objective_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjective_decl(ModelParser.Objective_declContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectiveExpectation}
	 * labeled alternative in {@link ModelParser#objective_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectiveExpectation(ModelParser.ObjectiveExpectationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectiveBooleanProbability}
	 * labeled alternative in {@link ModelParser#objective_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectiveBooleanProbability(ModelParser.ObjectiveBooleanProbabilityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectiveProbability}
	 * labeled alternative in {@link ModelParser#objective_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectiveProbability(ModelParser.ObjectiveProbabilityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectivePercentile}
	 * labeled alternative in {@link ModelParser#objective_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectivePercentile(ModelParser.ObjectivePercentileContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#quality_var_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuality_var_decl(ModelParser.Quality_var_declContext ctx);
	/**
	 * Visit a parse tree produced by the {@code qualityVariableDecision}
	 * labeled alternative in {@link ModelParser#quality_var_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualityVariableDecision(ModelParser.QualityVariableDecisionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code qualityVariableArithmetic}
	 * labeled alternative in {@link ModelParser#quality_var_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualityVariableArithmetic(ModelParser.QualityVariableArithmeticContext ctx);
	/**
	 * Visit a parse tree produced by the {@code qualityVariableParameter}
	 * labeled alternative in {@link ModelParser#quality_var_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualityVariableParameter(ModelParser.QualityVariableParameterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decisionXOR}
	 * labeled alternative in {@link ModelParser#decision_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecisionXOR(ModelParser.DecisionXORContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#decision_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecision_body(ModelParser.Decision_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#option_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption_name(ModelParser.Option_nameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code optionExpression}
	 * labeled alternative in {@link ModelParser#option_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptionExpression(ModelParser.OptionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code optionParameter}
	 * labeled alternative in {@link ModelParser#option_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptionParameter(ModelParser.OptionParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#parameter_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter_def(ModelParser.Parameter_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#distribution}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDistribution(ModelParser.DistributionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#distribution_args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDistribution_args(ModelParser.Distribution_argsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#distribution_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDistribution_arg(ModelParser.Distribution_argContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprNot}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNot(ModelParser.ExprNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprDiv}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprDiv(ModelParser.ExprDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprIdentifier}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprIdentifier(ModelParser.ExprIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprMult}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMult(ModelParser.ExprMultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprNumber}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNumber(ModelParser.ExprNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprCompare}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprCompare(ModelParser.ExprCompareContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPercent}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPercent(ModelParser.ExprPercentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprSub}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSub(ModelParser.ExprSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprOr}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprOr(ModelParser.ExprOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPreOperator}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPreOperator(ModelParser.ExprPreOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprBracket}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprBracket(ModelParser.ExprBracketContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprAdd}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAdd(ModelParser.ExprAddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprAnd}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAnd(ModelParser.ExprAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPower}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPower(ModelParser.ExprPowerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compareVariables}
	 * labeled alternative in {@link ModelParser#comparision}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareVariables(ModelParser.CompareVariablesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compareExpression}
	 * labeled alternative in {@link ModelParser#comparision}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareExpression(ModelParser.CompareExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomicInteger}
	 * labeled alternative in {@link ModelParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomicInteger(ModelParser.AtomicIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomicFloat}
	 * labeled alternative in {@link ModelParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomicFloat(ModelParser.AtomicFloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomicDecimal}
	 * labeled alternative in {@link ModelParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomicDecimal(ModelParser.AtomicDecimalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#integerLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteral(ModelParser.IntegerLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#optimisationDirection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptimisationDirection(ModelParser.OptimisationDirectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#comparator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparator(ModelParser.ComparatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#var_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_name(ModelParser.Var_nameContext ctx);
}