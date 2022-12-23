package com.vdoichev;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестування класу Calculator")
class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @BeforeEach
    void setUp() {
    }

    @Nested

    @DisplayName("Тестування методу calculate")
    class calculateNested {
        @Test()
        @DisplayName("Додавання двох цифр 2+3")
        void editionTwoNumbers() {
            String expression = "2+3";
            double result = calculator.calculate(expression);
            assertEquals(5, result, "2+3 should be equal 5!");
        }

        @Test
        @DisplayName("Додавання двох цифр 5+8")
        void editionOtherTwoNumbers() {
            String expression = "5+8";
            double result = calculator.calculate(expression);
            assertEquals(13, result, "5+8 should be equal 13!");
        }

        @Test
        @DisplayName("Додавання двох чисел 11+25")
        void editionTwoDecimalNumbers() {
            String expression = "11+25";
            double result = calculator.calculate(expression);
            assertEquals(36, result, "11+25 should be equal 36!");
        }

        @Test
        @DisplayName("Додавання трьох чисел 11+25+12")
        void editionThreeDecimalNumbers() {
            String expression = "11+25+12";
            double result = calculator.calculate(expression);
            assertEquals(48, result, "11+25+12 should be equal 48!");
        }

        @Test
        @DisplayName("Додавання трьох чисел з пробілами 11 + 25 + 12")
        void editionThreeDecimalNumbersWithSpace() {
            String expression = "11 + 25 + 12";
            double result = calculator.calculate(expression);
            assertEquals(48, result, "11 + 25 + 12 should be equal 48!");
        }

        @Test
        @DisplayName("Віднімання двох цифр 3-2")
        void subtractionTwoNumbers() {
            String expression = "3-2";
            double result = calculator.calculate(expression);
            assertEquals(1, result, "3-2 should be equal 1!");
        }

        @Test
        @DisplayName("Віднімання та додавання чисел 24-12+32")
        void subtractionAndEditionNumbers() {
            String expression = "24-12+32";
            double result = calculator.calculate(expression);
            assertEquals(44, result, "24-12+32 should be equal 44!");
        }

        @Test
        @DisplayName("Відємне число на початку при додаванні -12+32")
        void firstSubtractionAndEditionNumbers() {
            String expression = "-12+32";
            double result = calculator.calculate(expression);
            assertEquals(20, result, "-12+32 should be equal 20!");
        }

        @Test
        @DisplayName("Додатне число на початку при відніманні +56-32")
        void firstEditionAndSubtractNumbers() {
            String expression = "+56-32";
            double result = calculator.calculate(expression);
            assertEquals(24, result, "+56-32 should be equal 24!");
        }

        @Test
        @DisplayName("Множення/ділення/віднімання/додавання чисел 10*2/5-2+8")
        void multiplicationAndDivisionAndSubtractionAndEditionNumbers() {
            String expression = "10*2/5-2+8";
            double result = calculator.calculate(expression);
            assertEquals(10, result, "10*2/5-2+8 should be equal 10!");
        }

        @Test
        @DisplayName("Множення/ділення/віднімання/додавання відємних чисел 10*-2/-5--2+-8")
        void multiplicationAndDivisionAndSubtractionAndEditionNegativeNumbers() {
            String expression = "10*-2/-5--2+-8";
            double result = calculator.calculate(expression);
            assertEquals(-2, result, "10*-2/-5--2+-8 should be equal 10!");
        }

        @Test
        @DisplayName("Додавання двохї чисел з плаваючою крапкою 2.5+3.6")
        void additionTwoDouble() {
            String expression = "2.5+3.6";
            double result = calculator.calculate(expression);
            assertEquals(6.1, result, "2.5+3.6 should be equal 6.1!");
        }

        @Test
        @DisplayName("Віднімання/додавання/множення/ділення чисел 30-5*2+3/1")
        void subtractionAndEditionMultiplicationAndDivisionNumbers() {
            String expression = "-30-5*2+3/1";
            double result = calculator.calculate(expression);
            assertEquals(-37, result, "30-5*2+3/1 should be equal 23!");
        }
    }

    @Nested
    @DisplayName("Тестування методів виділення чисел та операторів зі строки")
    class extractNumbersNested {
        @Test
        @DisplayName("Дві цифри у виразі 1+2")
        void countTwoNumbersInExpression() {
            String expression = "1+2";
            calculator.calculate(expression);
            double result = calculator.getCountNumbers();
            assertEquals(2, result, "Expression 1+2 should be contain 2 numbers!");
        }

        @Test
        @DisplayName("Три цифри у виразі 1+2+3")
        void countThreeNumbersInExpression() {
            String expression = "1+2+3";
            calculator.calculate(expression);
            double result = calculator.getCountNumbers();
            assertEquals(3, result, "Expression 1+2+3 should be contain 3 numbers!");
        }

        @Test
        @DisplayName("Один оператор '+' у виразі 1+2")
        void countOneOperatorInExpression() {
            String expression = "1+2";
            calculator.calculate(expression);
            int result = calculator.getCountOperators();
            assertEquals(2, result, "Expression 1+2 should be contain 2 operator!");
        }

        @Test
        @DisplayName("Декілька різних операторів у виразі -1+2*4/2")
        void countManyOperatorsInExpression() {
            String expression = "-1+2*4/2";
            calculator.calculate(expression);
            int result = calculator.getCountOperators();
            assertEquals(4, result, "Expression -1+2*4/2 should be contain 4 operator!");
        }
    }
    @Nested
    @DisplayName("Тестування валідації виразу")
    class validateNumbersAndValidate {
        @Test
        @DisplayName("Відсутність зайвих символів у виразі -1*6/3+8-5")
        void expressionNotContainLetters() {
            String expression = "-1 *6/3+ 8-5";
            assertTrue(calculator.validateExpression(expression), "Expression -1*6/3+8-5 not contain letters or other symbols!");
        }

        @Test
        @DisplayName("Наявність літери у виразі 1+A-5")
        void expressionContainLetters() {
            String expression = "1+A-5";
            assertFalse(calculator.validateExpression(expression), "Expression 1+A-5 contain letters!");
        }

        @Test
        @DisplayName("Наявність коми у виразі 1+2,3-5")
        void expressionContainSymbols() {
            String expression = "1+2,3-5";
            assertFalse(calculator.validateExpression(expression), "Expression 1+2,3-5 contain other symbols!");
        }

        @Test
        @DisplayName("Наявність скобок у виразі (2+3)")
        void expressionContainBrackets() {
            String expression = "(2+3)";
            assertTrue(calculator.validateExpression(expression), "Expression (2+3)  should be contain brackets!");
        }

        @Test
        @DisplayName("Наявність скобок у виразі (2+3)+(5-3)")
        void expressionContainTwoBrackets() {
            String expression = "(2+3)+(5-3)";
            assertTrue(calculator.validateExpression(expression), "Expression (2+3)+(5-3)  should be contain brackets!");
        }

        @Test
        @DisplayName("Наявність вкладених скобок у виразі 2+(3-2*(5-2))")
        void expressionContainInnerBrackets() {
            String expression = "2+( 3-2* ( 5-2 ) )";
            assertTrue(calculator.validateExpression(expression), "Expression 2+(3-2*(5-2))  should be contain brackets!");
        }

        @Test
        @DisplayName("Наявність вкладених скобок у виразі 2+(3-2*(5-2))")
        void expressionContainIncorrectInnerBrackets() {
            String expression = "2+(3-2*)5-2))";
            assertFalse(calculator.validateExpression(expression), "Expression 2+(3-2*(5-2))  contain incorrect brackets!");
        }

        @Test
        @DisplayName("Наявність значної кількості арифметичних виразів 2++-1")
        void manyOperatorsInExpression() {
            String expression = "2++-1";
            assertFalse(calculator.validateExpression(expression), "Expression 2++-1  contain incorrect count operators!");
        }

        @Test
        @DisplayName("Перевірка другого символу в послідовності кількох арифметичних виразів 2+*1")
        void inCorrectOperatorsInExpression() {
            String expression = "2+*1";
            assertFalse(calculator.validateExpression(expression), "Expression 2+*1  contain incorrect operators!");
        }
    }

    @Nested
    @DisplayName("Тестування виразів зі скобками")
    class expressionWithBrackets {
        @Test
        @DisplayName("Додавання двох цифр (2+3)")
        void editionTwoNumbersWithBrackets() {
            String expression = "(2+3)";
            double result = calculator.calculate(expression);
            assertEquals(5, result, "Expression (2+3) should be equal 5!");
        }

        @Test
        @DisplayName("Вираз з двома блоками в дужках (2+3)-(5-2)")
        void expressionWithTwoBrackets() {
            String expression = "(2+3)-(5-2)";
            double result = calculator.calculate(expression);
            assertEquals(2, result, "Expression (2+3)-(5-2) should be equal 2!");
        }
    }
}
