package qls.ast.widget;

import qls.ast.Widget;
import qls.ast.visitor.Visitor;

public class RadioButton extends Widget {
	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
