package parser.nodes.expression;

import parser.Visitor;

/**
 * Created by Steven Kok on 21/02/2015.
 */
public class And extends BinaryExpression {
    public And(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
