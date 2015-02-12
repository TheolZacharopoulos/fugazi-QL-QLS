require 'strscan'
require "byebug"

module QL
  class Tokenizer
    WHITESPACE    = /\s+/
    KEYWORD       = /form|if|else|end|integer|boolean|string/
    VARIABLE_NAME = /[a-zA-Z0-9?!_]+/
    STRING        = /"(?:[^"\\]|\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4}))*"/

    def initialize io
      @ss = StringScanner.new io.read
    end

    def next_token
      return nil if @ss.eos?

      case
      when text = @ss.scan(WHITESPACE)    then next_token
      when text = @ss.scan(KEYWORD)       then [text, text]
      when text = @ss.scan(VARIABLE_NAME) then [:VARIABLE_NAME, text]
      when text = @ss.scan(STRING)        then [:STRING, text]
      else
        x = @ss.getch
        [x, x]
      end
    end
  end
end
