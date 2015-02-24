package org.uva.student.calinwouter.qlqls.application.gui.qls;

import org.uva.student.calinwouter.qlqls.ql.interpreter.TypeDescriptor;
import org.uva.student.calinwouter.qlqls.qls.model.functions.*;
import org.uva.student.calinwouter.qlqls.qls.model.interfaces.IModel;

import java.util.HashMap;

// TODO THIS IS A DUPLICATE OF ABSTRACTMODEL!!!!
public abstract class AbstractRenderer implements IModel {
    @Override
    public void caseHashMap(HashMap<String, Object> hashMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseString(String string) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseInteger(Integer integer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseStyleSheet(StyleSheet styleSheet) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseDefault(Default defaultSetting) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void casePage(Page page) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseSection(Section section) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseTypeDescriptor(TypeDescriptor<?> typeDescriptor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseQuestion(Question question) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseComputedValue(ComputedValue computedValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseRadio(Radio radio) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseSpinbox(Spinbox radio) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseSlider(Slider slider) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseCheckbox(Checkbox checkbox) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseTextbox(Textbox checkbox) {
        throw new UnsupportedOperationException();
    }
}
