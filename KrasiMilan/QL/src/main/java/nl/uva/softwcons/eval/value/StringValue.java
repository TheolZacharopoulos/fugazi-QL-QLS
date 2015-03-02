package nl.uva.softwcons.eval.value;

public class StringValue extends Value {

    private final String stringValue;

    public StringValue(String value) {
        this.stringValue = value;
    }

    @Override
    public String asString() {
        return stringValue;
    }

    @Override
    public BooleanValue isEqual(Value otherValue) {
        return new BooleanValue(this.stringValue != null && this.stringValue.equals(otherValue.asString()));
    }

    @Override
    public String getValue() {
        return this.stringValue;
    }

    @Override
    public Value getValueFromString(String string) {
        // TODO Auto-generated method stub
        return new StringValue(string);
    }
}
