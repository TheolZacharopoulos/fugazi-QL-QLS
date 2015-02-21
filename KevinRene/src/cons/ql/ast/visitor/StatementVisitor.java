package cons.ql.ast.visitor;

import cons.ql.ast.Statement;
import cons.ql.ast.statement.Block;
import cons.ql.ast.statement.ComputedQuestion;
import cons.ql.ast.statement.Form;
import cons.ql.ast.statement.If;
import cons.ql.ast.statement.Question;

public interface StatementVisitor<T> extends Visitor<Void> {
	default Void visit(Block blockNode) {
		for(Statement statement : blockNode.statements()) {
			statement.accept(this);
		}
		return null;
	}
	
	default Void visit(ComputedQuestion compQuestionNode) {
		compQuestionNode.getIdentifier().accept(this);
		compQuestionNode.getType().accept(this);
		compQuestionNode.getText().accept(this);
		compQuestionNode.getExpression().accept(this);
		return null;
	}
	
	default Void visit(Form formNode) {
		formNode.getIdentifier().accept(this);
		formNode.getBlock().accept(this);
		return null;
	}
	
	default Void visit(If ifNode) {
		ifNode.getExpression().accept(this);
		ifNode.getBlock().accept(this);
		return null;
	}
	
	default Void visit(Question questionNode) {
		questionNode.getIdentifier().accept(this);
		questionNode.getType().accept(this);
		questionNode.getText().accept(this);
		return null;
	}
}
