package uva.qls.ast.style;

import uva.qls.ast.CodeLines;
import uva.qls.ast.literal.IntLiteral;
import uva.qls.ast.value.NumberValue;

public class FontSize extends Font{

	private IntLiteral value;
	
	public FontSize(IntLiteral _value, CodeLines _codeLines) {
		super(_codeLines);
		this.value = _value;
	}

	public Integer evaluatedValue(){
		return this.evaluate().getValue().intValue();
	}
	
	@Override
	public NumberValue evaluate() {
		return new NumberValue(this.value.evaluatedValue());
	}

	@Override
	public String toString() {
		return "FontSize(" + this.evaluatedValue().toString() + ")";
	}

}
