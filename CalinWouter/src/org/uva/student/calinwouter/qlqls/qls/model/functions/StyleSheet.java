package org.uva.student.calinwouter.qlqls.qls.model.functions;

import org.uva.student.calinwouter.qlqls.qls.model.abstractions.AbstractComponent;
import org.uva.student.calinwouter.qlqls.qls.model.interfaces.IModel;

import java.util.LinkedList;
import java.util.List;

public class StyleSheet extends AbstractComponent<StyleSheet> {
    private String ident;
    private List<Page> pages;
    private List<Default> defaultSettings;
    private int arg;

    public String getStyleSheetName() {
        return ident;
    }

    public List<Page> getPages() {
        return pages;
    }

    public List<Default> getDefaultSettings() {
        return defaultSettings;
    }

    @Override
    public void caseString(String string) {
        if (arg != 0) {
            super.caseString(string);
            return;
        }
        this.ident = string;
        arg++;
    }

    @Override
    public void caseDefault(Default defaultSetting) {
        defaultSettings.add(defaultSetting);
    }

    @Override
    public void casePage(Page page) {
        page.setParent(this);
        pages.add(page);
    }

    @Override
    public void apply(IModel iModel) {
        iModel.caseStyleSheet(this);
    }

    public StyleSheet() {
        pages = new LinkedList<Page>();
        defaultSettings = new LinkedList<Default>();
    }
}
