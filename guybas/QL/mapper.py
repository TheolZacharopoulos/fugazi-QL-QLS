from type_checker import *
from exceptions import *


class Mapper:
    # map from id to answers
    def __init__(self):
        self.answers  = {}
        self.inputObj = {}

    def update(self, question, answer):
        qid = question.get_id()
        if not TypeChecker.type_checker(answer, question.get_type()):
            raise QException("Answer type has different type than defined in the question properties.")
        self.answers[qid] = answer

    def get_answers(self):
        return self.answers

    def get_answer_by_id(self, qid):
        if qid in self.answers.keys():
            return self.answers[qid]
        raise QException("Answer id " + qid + " does not exist.")

    def id_exists(self, qid):
        if qid in self.answers.keys():
            return True
        return False