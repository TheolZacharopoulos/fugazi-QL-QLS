package qls.ast;

import ql.ast.expression.Literal;
import qls.ast.stylerule.StyleProperty;
import qls.ast.visitor.QLSStatementVisitor;

@SuppressWarnings("rawtypes")
public class StyleRule extends QLSStatement {
	
	StyleProperty property;
	Literal value;
	
	public StyleRule(StyleProperty property, Literal value) {
		this.property = property;
		this.value = value;
	}

	@Override
	public <T> T accept(QLSStatementVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {		
		return property.toString() + ": " + value.getValue();
	}

}
