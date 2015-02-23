package nl.uva.bromance.AST.Conditionals;

import nl.uva.bromance.AST.Calculation;
import nl.uva.bromance.AST.Form;
import nl.uva.bromance.AST.Label;
import nl.uva.bromance.AST.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Gerrit Krijnen on 2/16/2015.
 */
public class ElseIfStatement extends Node implements ContainsExpression {
    private static final List<Class<? extends Node>> parentsAllowed = new ArrayList<Class<? extends Node>>(Arrays.asList(Form.class, Label.class, Calculation.class));
    private Expression expression;

    public ElseIfStatement(int lineNumber) {
        super(lineNumber, ElseIfStatement.class);
        super.setAcceptedParents(parentsAllowed);
    }

    @Override
    public Expression getExpression() {
        return expression;
    }

    @Override
    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
