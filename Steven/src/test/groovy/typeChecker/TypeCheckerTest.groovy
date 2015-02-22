package typeChecker

import exceptions.TypeCheckException
import parser.ParseTreeWalker
import parser.QLBaseVisitorImpl
import parser.nodes.Form
import spock.lang.Specification

/**
 * Created by Steven Kok on 21/02/2015.
 */
class TypeCheckerTest extends Specification {

    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();

    def "Should throw exception with duplicate questions"() {
        when:
        Form form = parseTreeWalker.walk(input, new QLBaseVisitorImpl())

        TypeChecker typeChecker = new TypeChecker();
        typeChecker.visit(form)

        then:
        def exception = thrown(TypeCheckException)
        exception.message.contains(message)

        where:
        input                                                     | message
        "src/main/antlr/input/QL_duplicateQuestions"              | TypeChecker.ALREADY_DECLARED_QUESTION.substring(0, 20)
        "src/main/antlr/input/QL_duplicateQuestionsDifferentType" | TypeChecker.ALREADY_DECLARED_QUESTION_DIFFERENT_TYPE.substring(0, 20)
    }
}
