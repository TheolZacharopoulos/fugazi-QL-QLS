package test.klq.typechecker;

import com.klq.ast.impl.expr.AExpression;
import com.klq.ast.impl.expr.bool.GreaterThanNode;
import com.klq.ast.impl.expr.literal.IdentifierNode;
import com.klq.ast.impl.expr.literal.NumberNode;
import com.klq.ast.impl.expr.literal.StringNode;
import com.klq.ast.impl.stmt.ComputedQuestionNode;
import com.klq.ast.impl.stmt.QuestionNode;
import com.klq.ast.impl.stmt.QuestionnaireNode;
import com.klq.typechecker.TypeChecker;
import com.klq.typechecker.error.NotUniqueID;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by Juriaan on 28-2-2015.
 */
public class DuplicateQuestionIdTest {
    private QuestionnaireNode ast;
    @Before
    public void setUp() throws Exception {
        ast = new QuestionnaireNode();
    }

    @Test
    public void testDuplicateQuestionId(){
        ast.getChildren().add(new QuestionNode(new IdentifierNode("question1"), "string", "This is a test question"));
        ast.getChildren().add(new QuestionNode(new IdentifierNode("question2"), "numeral", "This is another test question"));
        TypeChecker tc = new TypeChecker(ast);
        tc.run();
        assertEquals(0, tc.getErrors().size());

        ast.getChildren().add(new QuestionNode(new IdentifierNode("question1"), "numeral", "This is another test question, but with a duplicate ID"));
        tc = new TypeChecker(ast);
        tc.run();
        assertEquals(1,tc.getErrors().size());
        assertThat(tc.getErrors().get(0), instanceOf(NotUniqueID.class));

        List<AExpression> list = new ArrayList<AExpression>();
        list.add(new StringNode("test"));
        AExpression cond = new StringNode("test");
        ast.getChildren().add(new ComputedQuestionNode(new IdentifierNode("question1"), "string", "This is another test question, but with a duplicate ID", cond));
        tc = new TypeChecker(ast);
        tc.run();
        assertEquals(2, tc.getErrors().size());
    }
}
