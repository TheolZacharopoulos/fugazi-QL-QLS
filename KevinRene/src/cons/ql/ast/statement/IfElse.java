package cons.ql.ast.statement;

import cons.ql.ast.Expression;
import cons.ql.ast.Statement;
import cons.ql.ast.visitor.Visitor;

public class IfElse extends Statement {
	private final Expression expression;
	private final Block ifBranch;
	private final Block elseBranch;
	
	/**
	 * Constructor for the conditional block
	 * @param statement
	 */
	public IfElse(Expression expression, Block ifBranch, Block elseBranch) {
		this.expression = expression;
		this.ifBranch = ifBranch;
		this.elseBranch = elseBranch;
	}
	
	public Expression getExpression() {
		return this.expression;
	}
	
	public Block getIfBranch() {
		return this.ifBranch;
	}
	
	public Block getElseBranch() {
		return this.elseBranch;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("IfElse(");
		
		sb.append(getExpression().toString() + ", ");
		sb.append(getIfBranch().toString() + ", ");
		sb.append(getElseBranch().toString());
		sb.append(")");
		
		return sb.toString();
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {		
		return visitor.visit(this);
	}
}
