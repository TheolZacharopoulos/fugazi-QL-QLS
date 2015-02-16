﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UvA.SoftCon.Questionnaire.AST.Expressions;

namespace UvA.SoftCon.Questionnaire.AST.Literals
{
    /// <summary>
    /// Represents a static immutable boolean value.
    /// </summary>
    public class BooleanLiteral : Literal<bool>, IExpression
    {
        public BooleanLiteral(bool value)
            : base(value) { }

        public override string ToString()
        {
            return Value.ToString();
        }
    }
}
