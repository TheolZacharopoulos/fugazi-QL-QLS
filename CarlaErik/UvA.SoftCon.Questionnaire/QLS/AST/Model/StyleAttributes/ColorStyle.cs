﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UvA.SoftCon.Questionnaire.Common.AST.Model;
using UvA.SoftCon.Questionnaire.QLS.StyleSets;

namespace UvA.SoftCon.Questionnaire.QLS.AST.Model.StyleAttributes
{
    public class ColorStyle : StyleAttribute
    {
        public string RGBCode
        {
            get;
            private set;
        }

        internal ColorStyle(string rgbCode, TextPosition position)
            : base(position)
        {
            RGBCode = rgbCode;
        }

        public override T Accept<T>(IStyleSheetVisitor<T> visitor)
        {
            return visitor.VisitColorStyle(this);
        }

        public override void OverrideStyle(StyleSet styleSet)
        {
            styleSet.OverrideColor(RGBCode);
        }

        public override string ToString()
        {
            return RGBCode;
        }
    }
}
