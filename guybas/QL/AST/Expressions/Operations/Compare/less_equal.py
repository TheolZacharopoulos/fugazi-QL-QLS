import QL.AST.Expressions.Operations.Compare.equal as e


class LessEqual(e.Equal):

    def set_string_operator(self):
        return "<="

    def eval(self, x, y):
        return x <= y