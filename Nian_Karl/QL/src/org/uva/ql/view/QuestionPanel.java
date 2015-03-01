package org.uva.ql.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import org.uva.ql.ast.statement.QuestionNormal;
import org.uva.ql.view.widgit.QLLabel;
import org.uva.ql.view.widgit.QLWidget;

public class QuestionPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private QuestionNormal question;
	private QLLabel label;
	private final QLWidget<?> widget;
	
	public QuestionPanel(QuestionNormal question, QLWidget<?> widget) {
		super();
		this.question = question;
		this.label = new QLLabel(question.getText());
		this.widget = widget;
		setPreferredSize(new Dimension(350, 50));
		setBackground(Color.lightGray);
		add(label);
		add((Component) this.widget);
	}

	public QLWidget<?> getWidget() {
		return widget;
	}
	
	public QuestionNormal getQuestion() {
		return question;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}