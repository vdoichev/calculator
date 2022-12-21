package com.vdoichev;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тестування класу Calculator")
class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @BeforeEach
    void setUp() {
    }

    @Nested
    @DisplayName("Тестування методу calculate")
    class calculateNested {
        @Test
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
    }

    @Nested
    @DisplayName("Тестування методів виділення чисел та операторів зі строки")
    class extractNumbersNested {
        @Test
        @DisplayName("Дві цифри у виразі 1+2")
        void countTwoNumbersInExpression() {
            String expression = "1+2";
            double result = calculator.getCountNumbers(expression);
            assertEquals(2, result, "Expression 1+2 should be contain 2 numbers!");
        }

        @Test
        @DisplayName("Три цифри у виразі 1+2+3")
        void countThreeNumbersInExpression() {
            String expression = "1+2+3";
            double result = calculator.getCountNumbers(expression);
            assertEquals(3, result, "Expression 1+2+3 should be contain 3 numbers!");
        }

        @Test
        @DisplayName("Один оператор '+' у виразі 1+2")
        void countOneOperatorInExpression() {
            String expression = "1+2";
            int result = calculator.getCountOperators(expression);
            assertEquals(2, result, "Expression 1+2 should be contain 2 operator!");
        }

        @Test
        @DisplayName("Декілька різних операторів у виразі -1+2*4/2")
        void countManyOperatorsInExpression() {
            String expression = "-1+2*4/2";
            int result = calculator.getCountOperators(expression);
            assertEquals(4, result, "Expression -1+2*4/2 should be contain 4 operator!");
        }
    }
}
