/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.uva.student.calinwouter.qlqls.generated.node;

import org.uva.student.calinwouter.qlqls.generated.analysis.Analysis;

@SuppressWarnings("nls")
public final class TRparen extends Token {
    public TRparen() {
        super.setText(")");
    }

    public TRparen(int line, int pos) {
        super.setText(")");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone() {
        return new TRparen(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw) {
        ((Analysis) sw).caseTRparen(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text) {
        throw new RuntimeException("Cannot change TRparen text.");
    }
}
