# Generated from java-escape by ANTLR 4.5
from antlr4 import *
from QLVisitor import QLVisitor
from QLExceptions import IllegalOperatorError
from QLParser import QLParser
from AST import *

# This class defines a complete generic visitor for a parse tree produced by QLParser.
class CustomQLVisitor(QLVisitor):
    # Visit a parse tree produced by QLParser#statement.
    def visitStatement(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#form.
    def visitForm_statement(self, ctx):
        name = self.visit(ctx.getChild(1))
        statements = []
        for child in ctx.getChildren():
            if isinstance(child, QLParser.StatementContext):
                statements.append(self.visit(child))
        return FormStatementNode(name, statements)


    # Visit a parse tree produced by QLParser
    def visitQuestion_statement(self, ctx):
        name = self.visit(ctx.getChild(1))
        text = ctx.getChild(3).getText()
        question_type = ctx.getChild(4).getText()
        
        if isinstance(ctx.getChild(ctx.getChildCount() - 2), QLParser.ExprContext):
            expr = self.visit(ctx.getChild(ctx.getChildCount() - 2))
        else:
            expr = None

        return QuestionStatementNode(name, text, question_type, expr = expr)
        #return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#if_statement.
    def visitIf_statement(self, ctx):
        expr = self.visit(ctx.getChild(1))

        statements = []
        for child in ctx.getChildren():
            if isinstance(child, QLParser.StatementContext):
                statements.append(self.visit(child))
        return IfStatementNode(expr, statements)

        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#boolean.
    def visitBoolean(self, ctx):
        return self.ctx.getText() == 'true';

    # Visit a parse tree produced by QLParser#question_type.
    def visitQuestion_type(self, ctx):
        return ctx.getText()

    # Visit a parse tree produced by QLParser#string.
    def visitString(self, ctx):
        return ctx.getText()


    # Visit a parse tree produced by QLParser#integer.
    def visitInteger(self, ctx):
        return int(ctx.getText())

    # Visit a parse tree produced by QLParser#money.
    def visitMoney(self, ctx): # TODO
        return Money(ctx.getText())

    # Visit a parse tree produced by QLParser#identifier.
    def visitIdentifier(self, ctx): # TODO
        return ctx.getText()
    
    # Visit a parse tree produced by QLParser#atom.
    def visitAtom(self, ctx):
        return AtomicExpressionNode(self.visitChildren(ctx))


    # Visit a parse tree produced by QLParser#expr.
    def visitExpr(self, ctx):
        # no operator in expression (atom)
        if ctx.op == None:
            return self.visitChildren(ctx)

        op = ctx.op.text
        right = self.visit(ctx.right)

        # unary (rightside) operator
        if ctx.left == None:
            return UnaryExpressionNode(op, right)

        left = self.visit(ctx.left)

        return BinaryExpressionNode(left, op, right)