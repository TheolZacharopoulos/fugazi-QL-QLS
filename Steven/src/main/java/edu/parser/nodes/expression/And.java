package edu.parser.nodes.expression;

import edu.parser.Visitor;
import edu.parser.nodes.AbstractNode;

/**
 * Created by Steven Kok on 21/02/2015.
 */
public class And extends BinaryExpression {
    public And(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public AbstractNode accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean isConditional() {
        return true;
    }
}
