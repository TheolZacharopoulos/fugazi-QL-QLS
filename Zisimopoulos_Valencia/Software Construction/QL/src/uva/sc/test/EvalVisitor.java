package uva.sc.test;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

import org.antlr.v4.runtime.misc.NotNull;

import uva.sc.logic.Node;
import uva.sc.ast.NodeTree;
import uva.sc.logic.Form;
import uva.sc.logic.Question;
import uva.sc.logic.Statement;
import uva.sc.logic.binaryExpressions.Addition;
import uva.sc.parser.GrammarBaseVisitor;
import uva.sc.parser.GrammarParser;
import uva.sc.parser.GrammarParser.StatContext;

public class EvalVisitor extends GrammarBaseVisitor<Node> {
	
	@Override
	public Form visitForm(GrammarParser.FormContext ctx) {
		List<Statement> statementList = new ArrayList<Statement>();
		for (int i = 0 ; i < ctx.sts.size() ; i++) 
			statementList.add((Statement)visitStat(ctx.sts.get(i)));
		return new Form(ctx.ID().getText(), statementList);
	}
	
	
	
	
	
	
	@Override
	public Addition visitAdditive(GrammarParser.AdditiveContext ctx) {
		Value firstOperand = this.visit(ctx.expr(0));
		Value secondOperand = this.visit(ctx.expr(1));
		NodeTree<String> tree = new NodeTree<String>(new String("+"));
		tree.addChild(firstOperand);
		tree.addChild(secondOperand);
		return null;
	}
	
	@Override 
	public Question visitQuestion(GrammarParser.QuestionContext ctx) {
		return new Question(ctx.STRING(), ctx.ID(), visitType(ctx.type()), visit ctx.expr());
	}

	@Override
	public Addition visitId(GrammarParser.IdContext ctx) {
		Value returnValue = null;
		String id = ctx.ID().getText();
		if (memory.containsKey(id))
			returnValue = memory.get(id); 
		return returnValue;
	}

	@Override
	public Value visitString(GrammarParser.StringContext ctx) {
		String str = ctx.getText();
		str = str.substring(1, str.length() - 1).replace("\"\"", "\"");
		return new Value(str);
	}
	
	@Override
	public Value visitNumber(GrammarParser.NumberContext ctx) {
		return new Value(Double.valueOf(ctx.getText()));
	}
	
	@Override
	public Value visitBoolean(GrammarParser.BooleanContext ctx) {
		return new Value(Boolean.valueOf(ctx.getText()));
	}
	
	@Override
	public Value visitParenthesis(GrammarParser.ParenthesisContext ctx) {
		return this.visit(ctx.expr());
	}
	
	@Override
	public Value visitPower(GrammarParser.PowerContext ctx) {
		Value base = this.visit(ctx.expr(0));
		Value exponent = this.visit(ctx.expr(1));
		return new Value(Math.pow(base.asDouble(), exponent.asDouble()));
	}
	
	@Override
	public Value visitMultiplication(@NotNull GrammarParser.MultiplicationContext ctx) {
		Value firstOperand = this.visit(ctx.expr(0));
		Value secondOperand = this.visit(ctx.expr(1));
		Value returnValue = null;
		switch (ctx.op.getType()) {
			case GrammarParser.MULT:
				returnValue = new Value(firstOperand.asDouble() * secondOperand.asDouble());
			case GrammarParser.DIV:
				returnValue = new Value(firstOperand.asDouble() / secondOperand.asDouble());
			case GrammarParser.MOD:
				returnValue = new Value(firstOperand.asDouble() % secondOperand.asDouble());
			default:
		}
		return returnValue;
	}
	
	@Override
	public Value visitAnd(GrammarParser.AndContext ctx) {
		Value firstExpression = this.visit(ctx.expr(0));
		Value secondExpression = this.visit(ctx.expr(1));
		return new Value(firstExpression.asBoolean() && secondExpression.asBoolean());
	}
	
	@Override
	public Value visitOr(GrammarParser.OrContext ctx) {
		Value firstExpression = this.visit(ctx.expr(0));
		Value secondExpression = this.visit(ctx.expr(1));
		return new Value(firstExpression.asBoolean() || secondExpression.asBoolean());
	}
	
	@Override
	public Value visitIf_stat(GrammarParser.If_statContext ctx) {
		List<GrammarParser.ExprContext> expressions = ctx.expr();
		boolean result = false;
		for (GrammarParser.ExprContext expression : expressions) {
			
		}
	}
}
