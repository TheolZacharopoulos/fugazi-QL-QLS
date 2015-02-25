package nl.uva.bromance.AST.Conditionals;

import nl.uva.bromance.AST.Exceptions.InvalidOperandException;

/**
 * Created by Ger on 24-2-2015.
 */
public class EqualsOperator extends Operator {

    @Override
    public Result performOperation(Result one, Result two) throws InvalidOperandException {
        if (one.getClass() != two.getClass()){
            throw new InvalidOperandException();
        } else {
            return one.isEqual(two);
        }
    }
}
