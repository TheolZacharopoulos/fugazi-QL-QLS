package com.form.language.ast.expression.literal;

import com.form.language.ast.expression.Expression;
import com.form.language.error.QLToken;
import com.form.language.memory.Context;
import com.form.language.memory.IdCollection;

public abstract class Literal extends Expression {

    public Literal(QLToken tokenInfo) {
	super(tokenInfo);
    }

    @Override
    public Boolean isCorrectlyTyped(Context context) {
	return true;
    }

    @Override
    public void collectIds(IdCollection idCollection) {
    }

}
