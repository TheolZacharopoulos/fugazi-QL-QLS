﻿using System;
using QL.Model.Terminals;

namespace QL.Model.Operators
{
    public class DivisionOperator : BinaryTreeElementBase, IOperator, ITypeInferred
    {
        public ElementBase GetTypeInferableChild()
        {
            return Left;
        }
        
    }
}