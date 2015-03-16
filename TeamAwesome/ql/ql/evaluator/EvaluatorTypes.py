from ..CustomTypes import Money as CustomMoney
from ..CustomTypes import Identifier as CustomIdentifier
from ..TypeRules import nativeQuestionType

class BinaryExpression(object):
	def __init__(self, leftExpression, op, rightExpression, evaluator):
		self._left = leftExpression
		self._op = op
		self._right = rightExpression
		self._evaluator = evaluator

	def value(self):
		leftValue = self._left.value()
		rightValue = self._right.value()
		return self._evaluator.evaluateBinaryExpression(self._op, leftValue, rightValue)

class UnaryExpression(object):
	def __init__(self, op, expression, evaluator):
		self._op = op
		self._expression = expression
		self._evaluator = evaluator

	def value(self):
		exprValue = self._expression.value()
		return self._evaluator.evaluateUnaryExpression(self._op, exprValue)

class AtomicExpression(object):
	def __init__(self, value):
		self._value = value

	def value(self):
		return self._value.value()

class Form(object):
	def __init__(self, identifier, questions):
		self.identifier = identifier
		self.questions = questions

class Question(object):
	def __init__(self, questionStatementNode, conditionalExpressionsTuple, valueExpression = None):
		self.identifier = questionStatementNode.identifier
		self.valueExpression = valueExpression
		self.text = questionStatementNode.text
		self.type = nativeQuestionType(questionStatementNode.type)

		self.conditionalExpressions = conditionalExpressionsTuple
		self.constant = self.valueExpression != None

	def __str__(self):
		return "id:%s, text:%s, type:%s" %(self.identifier, self.text, self.type)

	def isVisible(self):
		return self.conditionalExpressions.value()


class EvaluatorBaseType(object):
	def __init__(self, value, evaluator = None):
		self._value = value
		self._evaluator = evaluator

	def __str__(self):
		return str(self.value())

	def value(self):
		return self._value

	def getType(self):
		raise NotImplementedError()

class Boolean(EvaluatorBaseType):
	def getType(self):
		return bool

class Integer(EvaluatorBaseType):
	def getType(self):
		return int

class String(EvaluatorBaseType):
	def getType(self):
		return str

class Money(EvaluatorBaseType):
	def getType(self):
		return CustomMoney

class Identifier(EvaluatorBaseType):
	def getType(self):
		return CustomIdentifier

	def value(self):
		return self._evaluator.getValue(self._value)