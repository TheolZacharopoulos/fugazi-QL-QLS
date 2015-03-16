﻿using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Text;
using UvA.SoftCon.Questionnaire.QL;
using UvA.SoftCon.Questionnaire.Runtime.Validation.QL;

namespace UvA.SoftCon.Questionnaire.Runtime.Test.Validation.QL
{
    /// <summary>
    /// Provides test methods for the <see cref="UvA.SoftCon.Questionnaire.Runtime.TypeChecking.VariableUsageCheckingVisitor"/> class.
    /// </summary>
    [TestClass]
    public class VariableUsageCheckingVisitorTest
    {
        [TestMethod]
        public void TestUnusedVariable()
        {
            // Arrange
            var ql = new StringBuilder();
            ql.AppendLine("bool isHappy = true"); // A declared variable should always be used in an expression.

            var controller = new QLController();
            var form = controller.ParseQLString(ql.ToString());

            var visitor = new VariableUsageChecker();

            // Act
            visitor.Visit(form);

            // Assert
            Assert.AreEqual<int>(1, visitor.UnusedVariables.Count);
            Assert.AreEqual<int>(0, visitor.UndeclaredVariables.Count);
            Assert.AreEqual<int>(0, visitor.RedeclaredVariables.Count);
        }

        [TestMethod]
        public void TestUnusedQuestion()
        {
            // Arrange
            var ql = new StringBuilder();
            ql.AppendLine("isHappy \"Are you happy?\" bool");  // A question doesn't need to be used in an expression.

            var controller = new QLController();
            var form = controller.ParseQLString(ql.ToString());

            var visitor = new VariableUsageChecker();

            // Act
            visitor.Visit(form);

            // Assert
            Assert.AreEqual<int>(0, visitor.UnusedVariables.Count);
            Assert.AreEqual<int>(0, visitor.UndeclaredVariables.Count);
            Assert.AreEqual<int>(0, visitor.RedeclaredVariables.Count);
        }


        [TestMethod]
        public void TestRedeclaredVariable()
        {
            // Arrange
            // Arrange
            var ql = new StringBuilder();
            ql.AppendLine("int age = 11");
            ql.AppendLine("string age= \"11\"");

            var controller = new QLController();
            var form = controller.ParseQLString(ql.ToString());

            var visitor = new VariableUsageChecker();

            // Act
            visitor.Visit(form);

            // Assert
            Assert.AreEqual<int>(1, visitor.UnusedVariables.Count);
            Assert.AreEqual<int>(0, visitor.UndeclaredVariables.Count);
            Assert.AreEqual<int>(1, visitor.RedeclaredVariables.Count);
        }
    }
}
