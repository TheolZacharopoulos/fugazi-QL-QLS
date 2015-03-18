package qls.ast.visitor;

import ql.ast.visitor.TypeVisitor;
import qls.ast.QLSStatement;
import qls.ast.statement.DefaultStyle;
import qls.ast.statement.DefaultWidget;
import qls.ast.statement.Page;
import qls.ast.statement.QLSBlock;
import qls.ast.statement.Question;
import qls.ast.statement.Section;
import qls.ast.statement.Stylesheet;
import qls.ast.statement.styling.Property;
import qls.ast.statement.styling.StyleProperties;
import qls.ast.statement.styling.property.Color;
import qls.ast.statement.styling.property.Font;
import qls.ast.statement.styling.property.FontSize;
import qls.ast.statement.styling.property.Height;
import qls.ast.statement.styling.property.Width;
import qls.ast.statement.widget.Widget;
import qls.ast.statement.widget.type.Checkbox;
import qls.ast.statement.widget.type.Default;
import qls.ast.statement.widget.type.Spinbox;
import qls.ast.statement.widget.type.TextField;
import qls.ast.statement.widget.type.parameterised.Dropdown;
import qls.ast.statement.widget.type.parameterised.RadioButton;
import qls.ast.statement.widget.type.parameterised.Slider;

/**
 * @author Rene
 */
public abstract class StatementVisitor<T> extends ql.ast.visitor.StatementVisitor<T> {
	private ExpressionVisitor<?> expressionVisitor;
	private TypeVisitor<?> typeVisitor;
	
	public void setExpressionVisitor(ExpressionVisitor<?> expressionVisitor) {
		this.expressionVisitor = expressionVisitor;
	}
	
	public void setTypeVisitor(TypeVisitor<?> typeVisitor) {
		this.typeVisitor = typeVisitor;
	}
	
	public T visit(Page pageNode) {
		pageNode.getIdentifier().accept(expressionVisitor);
		pageNode.getStatements().accept(this);
		return null;
	}
	
	public T visit(QLSBlock blockNode) {
		for(QLSStatement statement : blockNode.getStatements()) {
			statement.accept(this);
		}
		return null;
	}
	
	public T visit(Question questionNode) {
		questionNode.getIdentifier().accept(expressionVisitor);
		questionNode.getWidget().accept(this);
		return null;
	}
	
	public T visit(Section sectionNode) {
		sectionNode.getHeader().accept(expressionVisitor);
		sectionNode.getStatements().accept(this);
		return null;
	}
	
	public T visit(Stylesheet stylesheetNode) {
		stylesheetNode.getIdentifier().accept(expressionVisitor);
		stylesheetNode.getPages().accept(this);
		return null;
	}
	
	public T visit(Checkbox checkboxNode) {
		return null;
	}
	
	public T visit(Default defaultType) {
		return null;
	}
	
	public T visit(Dropdown dropdownNode) {
		return null;
	}
	
	public T visit(RadioButton radioButtonNode) {
		return null;
	}
	
	public T visit(TextField textFieldNode) {
		return null;
	}
	
	public T visit(Spinbox spinnerNode) {
		return null;
	}
	
	public T visit(Slider sliderNode) {
		return null;
	}

	public T visit(StyleProperties styleNode) {
		for(Property rule : styleNode.getProperties()) {
			rule.accept(this);
		}
		return null;
	}
	
	public T visit(DefaultStyle defaultNode) {
		defaultNode.getType().accept(typeVisitor);
		defaultNode.getStyleRuleSet().accept(this);
		return null;
	}	

	public T visit(DefaultWidget defaultNode) {
		defaultNode.getType().accept(typeVisitor);
		defaultNode.getWidget().accept(this);
		return null;
	}
	
	public T visit(Widget widgetNode) {
		widgetNode.getStyleRules().accept(this);
		widgetNode.getWidgetType().accept(this);
		return null;
	}


	public T visit(Color color) {
		color.getValue().accept(expressionVisitor);
		return null;
	}
	
	public T visit(Width width) {
		width.getValue().accept(expressionVisitor);
		return null;
	}
	
	public T visit(Height height) {
		height.getValue().accept(expressionVisitor);
		return null;
	}
	
	public T visit(Font font) {
		font.getValue().accept(expressionVisitor);
		return null;
	}
	public T visit(FontSize fontSize) {
		fontSize.getValue().accept(expressionVisitor);
		return null;
	}
}