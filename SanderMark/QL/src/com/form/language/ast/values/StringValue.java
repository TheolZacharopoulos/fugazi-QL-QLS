package com.form.language.ast.values;

import com.form.language.memory.Context;

public class StringValue extends GenericValue {
    private final String value;

    public StringValue(String value) {
	this.value = value;
    }

    public String getValue() {
	return value;
    }

    @Override
    public void addToMemory(String key, Context context) {
	context.setValue(key, this);
    }

    @Override
    public String toString() {
	// TODO Auto-generated method stub
	return "\"" + this.value + "\"";
    }
    
    @Override
    public Boolean equals(GenericValue comparison) {
	return this.value == ((StringValue) comparison).getValue();
    }

}
