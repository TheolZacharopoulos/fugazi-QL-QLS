package qls.ast.visitor.widgetbinder;

import ql.ast.visitor.TypeVisitor;
import qls.ast.expression.literal.BooleanLiteral;
import qls.ast.expression.literal.FloatLiteral;
import qls.ast.expression.literal.IntegerLiteral;
import qls.ast.expression.literal.StringLiteral;
import qls.ast.visitor.ExpressionVisitor;
import qls.ast.visitor.StatementVisitor;

public class WidgetBinder extends StatementVisitor<Void> implements ExpressionVisitor<Void>, TypeVisitor<Void> {
	@Override
	public Void visit(BooleanLiteral booleanLiteral) {
		return null;
	}

	@Override
	public Void visit(FloatLiteral floatLiteral) {
		return null;
	}

	@Override
	public Void visit(IntegerLiteral integerLiteral) {
		return null;
	}

	@Override
	public Void visit(StringLiteral stringLiteral) {
		return null;
	}
}
