package org.uva.student.calinwouter.ql.interpreter.components;

import org.uva.student.calinwouter.ql.generated.node.*;
import org.uva.student.calinwouter.ql.interpreter.interfaces.InterpreterInterface;
import org.uva.student.calinwouter.ql.interpreter.model.Environment;
import org.uva.student.calinwouter.ql.interpreter.model.QuestionModel;

import java.io.Console;
import java.util.LinkedList;
import java.util.Scanner;

public class PStmtInterpreter implements InterpreterInterface<PStmt> {

    @Override
    public void interprete(Environment e, PStmt node) {
        if (node instanceof AQuestionStmt) {
            AQuestionStmt questionStmt = (AQuestionStmt) node;

            e.getQuestionModels().add(
                    new QuestionModel(questionStmt.getIdent().getText(),
                    questionStmt.getStr().getText(), questionStmt.getType().toString()));
        } else if (node instanceof AValueStmt) {
            PExpIntepreter expI = new PExpIntepreter();
            expI.interprete(e, ((AValueStmt) node).getExp());
            System.out.println(((AValueStmt) node).getStr() + expI.getExpValue().toString());
        } else if (node instanceof AIfelseStmt) {
            PExpIntepreter expI =  new PExpIntepreter();
            expI.interprete(e, ((AIfelseStmt) node).getExp());
        } else if (node instanceof AIfStmt) {
            PExpIntepreter expI = new PExpIntepreter();
            expI.interprete(e, ((AIfStmt) node).getExp());
            if((Boolean) expI.getExpValue()){
                new PStmtlistInterpreter().interprete(e, ((AIfStmt) node).getIfstmts());
            }
        }
    }
}
