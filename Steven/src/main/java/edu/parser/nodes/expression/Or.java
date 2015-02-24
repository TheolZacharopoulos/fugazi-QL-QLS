package edu.parser.nodes.expression;

import edu.parser.Visitor;
import edu.parser.nodes.AbstractNode;

/**
 * Created by Steven Kok on 21/02/2015.
 */
public class Or extends BinaryExpression {
    public Or(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public AbstractNode accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean hasBooleanOperands() {
        return true;
    }
}
