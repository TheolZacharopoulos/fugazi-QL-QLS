package org.uva.sea.ql.AST.expression.booleanexpression;

import org.uva.sea.ql.AST.expression.BinaryExpression;
import org.uva.sea.ql.AST.expression.Expression;
import org.uva.sea.ql.AST.value.AbstractValue;

public class OrExpression extends BinaryExpression{
//	private BooleanLiteral leftLiteral = (BooleanLiteral) this.leftExpression;
//	private BooleanLiteral rightLiteral = (BooleanLiteral) this.rightExpression;
//	

	public OrExpression(Expression leftExpression, Expression rightExpression) {
		super(leftExpression, rightExpression);
	}

	@Override
	public AbstractValue<?> interpretExpression() {
		// TODO Auto-generated method stub
		return null;
	}
}
