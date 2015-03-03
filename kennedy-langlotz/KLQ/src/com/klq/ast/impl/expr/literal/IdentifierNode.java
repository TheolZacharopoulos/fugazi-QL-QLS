package com.klq.ast.impl.expr.literal;

import com.klq.ast.IVisitor;
import com.klq.ast.impl.expr.AExpression;
import com.klq.logic.value.IdentifierValue;
import com.klq.logic.value.Value;

import java.util.Map;

/**
 * Created by juriaan on 23-2-15.
 */
public class IdentifierNode extends AExpression {
    private String identifier;

    public IdentifierNode(String identifier, String location) {
        super(null, null, location);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Value evaluate(Map<IdentifierValue, Value> variables) {
        return variables.get(this);
    }
}
