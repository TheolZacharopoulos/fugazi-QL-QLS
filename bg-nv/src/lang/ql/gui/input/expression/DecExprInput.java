package lang.ql.gui.input.expression;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.VBox;
import lang.ql.ast.expression.Expr;
import lang.ql.gui.ModelVisitor;
import lang.ql.gui.control.Control;
import lang.ql.gui.control.ControlType;
import lang.ql.gui.control.DecimalControl;
import lang.ql.semantics.ValueTable;
import lang.ql.semantics.values.DecimalValue;
import lang.ql.semantics.values.StringValue;
import lang.ql.semantics.values.UndefinedValue;
import lang.ql.semantics.values.Value;

import java.math.BigDecimal;

/**
 * Created by Nik on 28-02-2015
 */
public class DecExprInput extends ExprInput
{
    private final DecimalControl control;

    public DecExprInput(String id, DecimalControl control, Expr expression)
    {
        this(id, control, expression, true);
        this.inputNode = this.createInputNode(control);
    }

    public DecExprInput(String id, DecimalControl control, Expr expression, Boolean visible)
    {
        super(id, expression, visible);
        this.control = control;
    }

    @Override
    protected VBox createInputNode(ControlType control)
    {
        VBox box = new VBox();
        box.getChildren().add(this.control.getGuiElement());
        box.setAlignment(Pos.TOP_RIGHT);
        box.setVisible(this.getVisible());
        return box;
    }

    @Override
    public <V> V accept(ModelVisitor<V> visitor)
    {
        return visitor.visit(this);
    }

    @Override
    public void refreshElement(ValueTable valueTable)
    {
        Value val = valueTable.getValue(this.getId());

        if (!val.isUndefined())
        {
            assert val instanceof DecimalValue;
            this.control.setValue((DecimalValue)val);
        }
        else 
        {
            assert val instanceof UndefinedValue;
            this.control.setValue((UndefinedValue)val);
        }
    }
}
