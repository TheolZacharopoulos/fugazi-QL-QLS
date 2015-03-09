package org.uva.student.calinwouter.qlqls.ql.helper;

import org.uva.student.calinwouter.qlqls.generated.lexer.Lexer;
import org.uva.student.calinwouter.qlqls.generated.lexer.LexerException;
import org.uva.student.calinwouter.qlqls.generated.node.*;
import org.uva.student.calinwouter.qlqls.generated.parser.Parser;
import org.uva.student.calinwouter.qlqls.generated.parser.ParserException;
import org.uva.student.calinwouter.qlqls.ql.interpreter.FormInterpreter;
import org.uva.student.calinwouter.qlqls.ql.interpreter.impl.headless.HeadlessFormInterpreter;
import org.uva.student.calinwouter.qlqls.ql.interpreter.impl.typechecker.FormTypeChecker;
import org.uva.student.calinwouter.qlqls.qls.QLSAdapter;
import org.uva.student.calinwouter.qlqls.qls.QLSTypeChecker;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

public class InterpreterHelper {

    private static void applyInterpreterUsing(String input, FormInterpreter formInterpreter) throws ParserException, IOException, LexerException {
        Lexer lexer = new Lexer(new PushbackReader(new StringReader(input)));
        Parser parser = new Parser(lexer);
        Start ast = parser.parse();
        AForm form = (AForm) ((AFormBegin) ast.getPBegin()).getForm();
        form.apply(formInterpreter);
    }

    public static FormTypeChecker typeCheckString(String input) throws ParserException, IOException, LexerException {
        FormTypeChecker formInterpreter = new FormTypeChecker();
        applyInterpreterUsing(input, formInterpreter);
        return formInterpreter;
    }

    public static QLSAdapter interpetStylesheetString(String input) throws ParserException, IOException, LexerException {
        QLSAdapter qlsInterpreter = new QLSAdapter();
        Lexer lexer = new Lexer(new PushbackReader(new StringReader(input)));
        Parser parser = new Parser(lexer);
        Start ast = parser.parse();
        PIdentList stylesheet = ((AStylesheetBegin) ast.getPBegin()).getIdentList();
        stylesheet.apply(qlsInterpreter);
        return qlsInterpreter;
    }

    public static HeadlessFormInterpreter initializeHeadlessInterpreter(String input) throws ParserException, IOException, LexerException {
        HeadlessFormInterpreter formInterpreter = new HeadlessFormInterpreter();
        applyInterpreterUsing(input, formInterpreter);
        return formInterpreter;
    }

    public static HeadlessFormInterpreter interpetStringHeadless(String input) throws ParserException, IOException, LexerException {
        HeadlessFormInterpreter headlessFormInterpreter = initializeHeadlessInterpreter(input);
        headlessFormInterpreter.interpret();
        return headlessFormInterpreter;
    }

    private InterpreterHelper() {
    }

}
