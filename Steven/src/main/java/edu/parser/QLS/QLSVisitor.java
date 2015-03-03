package edu.parser.QLS;

import edu.parser.QLS.nodes.AbstractNode;
import edu.parser.QLS.nodes.Identifier;
import edu.parser.QLS.nodes.Section;
import edu.parser.QLS.nodes.Stylesheet;
import edu.parser.QLS.nodes.statement.Default;
import edu.parser.QLS.nodes.statement.Page;
import edu.parser.QLS.nodes.statement.Question;
import edu.parser.QLS.nodes.styles.Color;
import edu.parser.QLS.nodes.styles.Font;
import edu.parser.QLS.nodes.styles.Widget;
import edu.parser.QLS.nodes.styles.Width;
import edu.parser.nodes.QuestionType;

/**
 * Created by Steven Kok on 28/02/2015.
 */
public interface QLSVisitor {
    AbstractNode visit(Stylesheet stylesheet);

    AbstractNode visit(Page page);

    AbstractNode visit(Question question);

    AbstractNode visit(Identifier identifier);

    AbstractNode visit(Section section);

    AbstractNode visit(Default aDefault);

    AbstractNode visit(Width width);

    AbstractNode visit(Widget widget);

    AbstractNode visit(Font font);

    AbstractNode visit(Color color);

    AbstractNode visit(QuestionType questionType);
}
