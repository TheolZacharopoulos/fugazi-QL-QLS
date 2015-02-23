package uva.ql.parser;

import org.antlr.v4.runtime.ParserRuleContext;

import uva.ql.ast.ASTNode;
import uva.ql.ast.CodeLines;
import uva.ql.ast.Form;
import uva.ql.ast.Prog;
import uva.ql.ast.expressions.Expression;
import uva.ql.ast.expressions.Operator;
import uva.ql.ast.expressions.PrimitiveType;
import uva.ql.ast.expressions.Type;
import uva.ql.ast.expressions.literals.Identifier;
import uva.ql.ast.expressions.literals.IntLiteral;
import uva.ql.ast.expressions.logic.And;
import uva.ql.ast.expressions.logic.Equal;
import uva.ql.ast.expressions.logic.Greater;
import uva.ql.ast.expressions.logic.Greater_Eq;
import uva.ql.ast.expressions.logic.Less;
import uva.ql.ast.expressions.logic.Less_Eq;
import uva.ql.ast.expressions.logic.NotEqual;
import uva.ql.ast.expressions.logic.Or;
import uva.ql.ast.expressions.math.Addition;
import uva.ql.ast.expressions.math.Division;
import uva.ql.ast.expressions.math.Exponentiation;
import uva.ql.ast.expressions.math.Multiplication;
import uva.ql.ast.expressions.math.Substraction;
import uva.ql.parser.QLParser.AssignExprContext;
import uva.ql.parser.QLParser.AssignStrContext;
import uva.ql.parser.QLParser.ExprContext;
import uva.ql.parser.QLParser.FormContext;
import uva.ql.parser.QLParser.IfStatementContext;
import uva.ql.parser.QLParser.LiteralContext;
import uva.ql.parser.QLParser.PrimitiveTypeContext;
import uva.ql.parser.QLParser.ProgContext;
import uva.ql.parser.QLParser.QuestContext;
import uva.ql.parser.QLParser.StatContext;
import uva.ql.ast.expressions.literals.*;
import uva.ql.ast.question.Question;
import uva.ql.ast.statements.Assign;
import uva.ql.ast.statements.IfStatement;
import uva.ql.ast.statements.Statement;

import java.util.*;

public class QLMainVisitor extends QLBaseVisitor<ASTNode> {
	
	public static List<StatContext> stats = new ArrayList<StatContext>();
	
	@Override 
	public ASTNode visitProg(ProgContext ctx) { 
		CodeLines codeLines = getCodeLines(ctx);
		return new Prog((Form)visitForm(ctx.form()), codeLines);
	}
	
	@Override 
	public ASTNode visitForm(FormContext ctx) { 
		CodeLines codeLines = getCodeLines(ctx);
		
		List<Statement> statement = new ArrayList<Statement>();
		for (StatContext s : ctx.stms){
			statement.add((Statement)visitStat(s));
		}
		
		return new Form(new Identifier(ctx.Identifier().getText(), codeLines), statement, codeLines);
	}
	
	@Override 
	public ASTNode visitQuest(QuestContext ctx) { 
		CodeLines codeLines = getCodeLines(ctx);
		
		List<Statement> statement = new ArrayList<Statement>();
		for(StatContext s : ctx.stms){
			if (s.quest() == null) statement.add((Statement)visitStat(s));
			else System.err.println("Nested questions");
		}
		
		return new Question(new Identifier(ctx.Identifier().getText(), codeLines), (Type)visitPrimitiveType(ctx.primitiveType()), statement, codeLines);
	}
	
	@Override 
	public ASTNode visitStat(StatContext ctx) { 
		
		if (ctx.expr() != null) return visitExpr(ctx.expr());
		else if (ctx.quest() != null) return visitQuest(ctx.quest());
		else if (ctx.ifStatement() != null) return visitIfStatement(ctx.ifStatement());
		else if (ctx.assign() != null){
			if (ctx.assign().getClass() == AssignExprContext.class) return visitAssignExpr((AssignExprContext)ctx.assign());
			else return visitAssignStr((AssignStrContext)ctx.assign());
		}
		
		return visitChildren(ctx); 
	}

	@Override
	public ASTNode visitAssignExpr(QLParser.AssignExprContext ctx) { 
		CodeLines codeLines = getCodeLines(ctx);
		return new Assign(new Identifier(ctx.Identifier().toString(), codeLines), (Expression)visitExpr(ctx.expr()), codeLines);
	}

	@Override 
	public ASTNode visitAssignStr(QLParser.AssignStrContext ctx) { 
		CodeLines codeLines = getCodeLines(ctx);
		return new Assign(new Identifier(ctx.Identifier().toString(), codeLines), new StringLiteral(ctx.STRING().getText(), codeLines), codeLines);
	}
	
	@Override 
	public ASTNode visitExpr(ExprContext ctx) { 
		CodeLines codeLines = getCodeLines(ctx);
		
		if (ctx.op != null){
			Operator operator = Operator.findOperator(ctx.op.getText().toString());
			
			switch(operator){
				case ADD: return new Addition((Expression)visitExpr(ctx.x),(Expression)visitExpr(ctx.y), codeLines);
				case SUB: return new Substraction((Expression)visitExpr(ctx.x),(Expression)visitExpr(ctx.y), codeLines); 
				case MUL: return new Multiplication((Expression)visitExpr(ctx.x),(Expression)visitExpr(ctx.y), codeLines);
				case DIV: return new Division((Expression)visitExpr(ctx.x),(Expression)visitExpr(ctx.y), codeLines);
				case EXP:  return new Exponentiation((Expression)visitExpr(ctx.x),(Expression)visitExpr(ctx.y), codeLines);
				case OR:   return new Or((Expression)visitExpr(ctx.x),(Expression)visitExpr(ctx.y), codeLines);
				case AND:  return new And((Expression)visitExpr(ctx.x),(Expression)visitExpr(ctx.y), codeLines);
				case LESS: return new Less((Expression)visitExpr(ctx.x),(Expression)visitExpr(ctx.y), codeLines);
				case LESS_EQ:  return new Less_Eq((Expression)visitExpr(ctx.x),(Expression)visitExpr(ctx.y), codeLines);
				case GREATER:  return new Greater((Expression)visitExpr(ctx.x),(Expression)visitExpr(ctx.y), codeLines);
				case GREATER_EQ:  return new Greater_Eq((Expression)visitExpr(ctx.x),(Expression)visitExpr(ctx.y), codeLines);
				case EQUAL:  return new Equal((Expression)visitExpr(ctx.x),(Expression)visitExpr(ctx.y), codeLines);
				case NOT_EQUAL:  return new NotEqual((Expression)visitExpr(ctx.x),(Expression)visitExpr(ctx.y), codeLines);
			}
		}
		else if (ctx.y == null && ctx.x != null){
			return visitExpr(ctx.x);
		}
		else{
			return visitLiteral(ctx.lit);
		}
		
		return visitChildren(ctx);
	}
	
	@Override 
	public ASTNode visitIfStatement(IfStatementContext ctx) {
		
		CodeLines codeLines = getCodeLines(ctx);
		
		List<Statement> statement = new ArrayList<Statement>();
		for(StatContext s : ctx.stms)
			statement.add((Statement)visitStat(s));
		
		return new IfStatement((Expression)visitExpr(ctx.expr()), statement, codeLines);
	}
	
	@Override 
	public ASTNode visitLiteral(LiteralContext ctx) {
		
		CodeLines codeLines = getCodeLines(ctx);
		
		if (ctx.BooleanLiteral() != null) 
			return new BooleanLiteral(Boolean.valueOf(ctx.getText()),codeLines);
		else if (ctx.Integer() != null)
			return new IntLiteral(Integer.valueOf(ctx.getText().replaceAll(".*\\(|\\).*", "")), codeLines);
		else if (ctx.Decimal() != null) 
			return new DecimalLiteral(Float.valueOf(ctx.getText().replaceAll(".*\\(|\\).*", "")), codeLines);
		else if (ctx.Identifier() != null){ 
			return new Identifier(ctx.getText(), codeLines);
		}
		
		return visitChildren(ctx); 
	}
	
	@Override 
	public ASTNode visitPrimitiveType(PrimitiveTypeContext ctx) { 
		PrimitiveType type = PrimitiveType.findOperator(ctx.getText());
		CodeLines codeLines = getCodeLines(ctx);
		
		switch(type){
			case BOOLEAN: 	return new Type(ctx.getText(), codeLines);
			case INT: 		return new Type(ctx.getText(), codeLines);
			case DECIMAL: 	return new Type(ctx.getText(), codeLines);
			case STRING: 	return new Type(ctx.getText(), codeLines);
		}
		return null;
	}
	
	// Visitor functions
	
	private CodeLines getCodeLines(ParserRuleContext ctx){
		return new CodeLines(ctx.start.getLine(),ctx.stop.getLine());
	}
	
}