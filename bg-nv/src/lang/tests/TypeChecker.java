package lang.tests;

import lang.ql.semantics.errors.*;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by bore on 21/02/15.
 */
public class TypeChecker
{
    @Test
    public void duplicateLabelsWarning() throws FileNotFoundException
    {
        List<Message> ms = TestHelper.analyse("duplicateLabels");
        assertEquals(1, ms.size());
        Warning w = TestHelper.as(ms.get(0), Warning.class);
        assertNotNull(w);
    }

    @Test
    public void ifCondBoolError() throws FileNotFoundException
    {
        List<Message> ms = TestHelper.analyse("ifCondIllegal");
        assertEquals(1, ms.size());
        TestHelper.assertErrorMessage(ms.get(0), "Error (Line 2): if statements should have conditions of type boolean");
    }

    @Test
    public void typeMismatchError() throws FileNotFoundException
    {
        List<Message> ms = TestHelper.analyse("typeMismatch");
        assertEquals(1, ms.size());
        TestHelper.assertErrorMessage(ms.get(0),
                "Error (Line 2): expression of type Add cannot have children of different type: integer and string");
    }

    @Test
    public void incorrectTypesError() throws FileNotFoundException
    {
        List<Message> ms = TestHelper.analyse("incorrectTypes");
        assertEquals(1, ms.size());

        TestHelper.assertErrorMessage(ms.get(0),"Error (Line 2): expression of type Or cannot children of type string");
    }

    @Test
    public void undeclaredIdError() throws FileNotFoundException
    {
        List<Message> ms = TestHelper.analyse("undeclaredIdentifier");
        assertEquals(1, ms.size());
        TestHelper.assertErrorMessage(ms.get(0),"Error (Line 2): identifier \"undeclId\" is not declared");
    }

    @Test
    public void defEvalMismatchError() throws FileNotFoundException
    {
        List<Message> ms = TestHelper.analyse("defEvalMismatch");
        assertEquals(1, ms.size());

        TestHelper.assertErrorMessage(ms.get(0),
                "Error (Line 2): Question \"quest\" is defined as type string, but is calculated as type boolean");;
    }

    @Test
    public void duplicateIdentifiersError() throws FileNotFoundException
    {
        List<Message> ms = TestHelper.analyse("duplicateIdentifiers");
        assertEquals(1, ms.size());

        TestHelper.assertErrorMessage(ms.get(0),
                "Error (Line 2): identifier \"hasSoldHouse\" is already declared twice on lines 2 and 3");
    }

    @Test
    public void identifierDeclaredOfDiffTypeError() throws FileNotFoundException
    {
        List<Message> ms = TestHelper.analyse("identDeclaredOfDiffType");
        assertEquals(1, ms.size());

        TestHelper.assertErrorMessage(ms.get(0),
                "Error (Line: 2): Question \"hasSoldHouse\" is declared twice with a different type on line 2 and 3");
    }

    @Test
    public void cyclicQuestionsError() throws FileNotFoundException
    {
        List<Message> ms = TestHelper.analyse("cyclicQuestions");
        assertEquals(1, ms.size());

        TestHelper.assertErrorMessage(ms.get(0), "Error: the following questions form a cyclic dependency: b, c, a");
    }

    @Test
    public void directCyclicQuestionsError() throws FileNotFoundException
    {
        List<Message> ms = TestHelper.analyse("directCyclicQuestions");
        assertEquals(1, ms.size());

        TestHelper.assertErrorMessage(ms.get(0), "Error: the following questions form a cyclic dependency: income");
    }

    @Test
    public void manyCyclicQuestionsError() throws FileNotFoundException
    {
        List<Message> ms =  TestHelper.analyse("manyCyclicQuestions");
        assertEquals(1, ms.size());

        TestHelper.assertErrorMessage(ms.get(0), "Error: the following questions form a cyclic dependency: tas, has, mas");
    }
}
