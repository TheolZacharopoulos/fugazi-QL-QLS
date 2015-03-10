package uva.sc.qls.logic.fonts;

import uva.sc.qls.ast.INodeVisitor;

public class TimesNewRoman implements FontType{

	public java.lang.String toString() {
		return "[FontType]: TimesNewRoman";
	}

	public boolean equals(FontType type) {
		if(type == null) {
			return false;
		}
		if(type instanceof TimesNewRoman) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public <T> T accept(INodeVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
