class QL::Parser
prechigh
  left     '*' '/'
  left     '+' '-'
  nonassoc '==' '!=' '<=' '<' '>=' '>'
  nonassoc '&&' '||' 
preclow

token STRING VARIABLE_NAME INTEGER
rule
  form
    : 'form' variable_name statements 'end' { result = Form.new(name: val[1], statements: val[2]) }
    ;
  statements
    : statements statement { result = val[0].push(val[1]) }
    | statement { result = [ val[0] ] }
    ;
  statement
    : question
    | conditional
    ;
  question
    : string variable_name ':' type { result = Question.new(description: val[0], variable_name: val[1], type: val[3].to_sym) }
    ;
  variable_name
    : VARIABLE_NAME 
    ;
  type
    : 'boolean'
    | 'integer'
    | 'string'
    ;

  conditional
    : if
    | if_else
    ;
  if
    : 'if' '(' expression ')' statements 'end' { result = If.new(expression: val[2], statements: val[4]) }
    ;
  if_else
    : 'if' '(' expression ')' statements 'else' statements 'end' { result = IfElse.new(expression: val[2], statements_true: val[4], statements_false: val[6]) }
    ;

  expression
    : expression '==' expression { result = Equal.new(val[0], val[2]) }
    | expression '<=' expression { result = LessThanOrEqualTo.new(val[0], val[2]) }
    | expression '<'  expression { result = LessThan.new(val[0], val[2]) }
    | expression '>=' expression { result = GreaterThanOrEqualTo.new(val[0], val[2]) }
    | expression '>'  expression { result = GreaterThan.new(val[0], val[2]) }
    | expression '!=' expression { result = Inequal.new(val[0], val[2]) }
    | expression '&&' expression { result = And.new(val[0], val[2]) }
    | expression '||' expression { result = Or.new(val[0], val[2]) }
    | expression '*'  expression { result = Multiplication.new(val[0], val[2]) }
    | expression '/'  expression { result = Division.new(val[0], val[2]) }
    | expression '+'  expression { result = Plus.new(val[0], val[2]) }
    | expression '-'  expression { result = Minus.new(val[0], val[2]) }
    | '(' expression ')'
    | constant
    | variable_name { result = Variable.new(val[0]) }
    ;

  constant
    : integer
    | string
    | boolean
    ;
 
  integer
    : INTEGER { result = IntegerLiteral.new(val[0].to_i) }
    ;
  string
    # TODO: get rid of double quotes in a nicer way.
    : STRING { result = StringLiteral.new(val[0][1..-2]) }
    ;
  boolean
    : 'true' { result = BooleanLiteral.new(true) } 
    | 'false' { result = BooleanLiteral.new(false) }
    ;
end

---- inner

  require_relative '../../ql'

  def initialize tokenizer
    @tokenizer = tokenizer
    super()
  end

  def next_token
    @tokenizer.next_token
  end

  def parse
    do_parse
  end
