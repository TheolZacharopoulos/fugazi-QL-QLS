# ast


class Expression:
    def __init__(self, expression):
        self.str_expression = expression
        self.is_else = False

    def evaluate(self):
        pass

    def dependencies(self):
        pass

    def ast_print(self, level=0):
        return "   " * level + self.str_expression


# Questions
class Question:
    def __init__(self, qid, qtype, label):
        self.id = qid
        self.label = label
        self.type = qtype

    def ast_print(self, level=0):
        s = "\n" + "   " * level + "Question:" + self.id + "\n"
        s += "   " * (level + 1) + self.label + "\n"
        s += "   " * (level + 1) + str(self.type)
        s += "\n"
        return s

    # Getters
    def get_label(self):
        return self.label

    def get_type(self):
        return self.type

    def get_id(self):
        return self.id


class IfQuestion(Question):
    def __init__(self, qid, qtype, label, condition):
        self.id = qid
        self.type = qtype
        self.label = label
        self.condition = condition

    def ast_print(self, level=0):
        s = "\n" + "   " * level + "If (" + self.condition.ast_print(0) + ")"
        s += super(IfQuestion, self).ast_print(level + 1)
        return s

class AdvancedQuestions(Question):
    def __init__(self, condition, questions):
        self.condition = condition
        self.questions = questions
        self.else_questions = []

    def add_else(self, questions):
        self.else_questions = questions

    def ast_print(self, level=0):
        s = "\n" + "   " * level + "If (" + self.condition.ast_print(0) + ")"
        for question in self.questions:
            s += "   " * level + question.ast_print(level+1)
        if self.else_questions:
            s += "   " * level + "else"
            for i in self.else_questions:
                s += "   " * level + i.ast_print(level+1)
        return s

    def get_c_questions(self):
        return self.questions

    def get_condition(self):
        return self.condition.ast_print()

    def get_id(self):
        ids = []
        for question in self.questions:
            ids.append(question.get_id())
        for question in self.else_questions:
            ids.append(question.get_id())
        return ids

    def get_label(self):
        labels = []
        for label in self.questions:
            labels.append(label.get_label())
        for question in self.else_questions:
            labels.append(label.get_label())
        print(labels)
        return labels

class Form:
    def __init__(self, name, introduction, questions):
        self.name = name 
        self.questions = questions
        self.introduction = introduction

    def ast_print(self):
        s = self.name + "\n"
        s += self.introduction + "\n"
        for i in self.questions:
            s += i.ast_print(1)
        return s

    def get_questions(self):
        return self.questions

    def get_name(self):
        return self.name

    def get_introduction(self):
        return self.introduction

class FormObject:
    def __init__(self):
        pass