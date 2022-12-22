package com.vdoichev;

import java.util.Arrays;

public class Calculator {

    public static final String REGEX_FOR_NUMBERS = "[^\\d.]+";
    public static final String REGEX_FOR_OPERATORS = "[^-+/*]+";

    public double calculate(String expression) {
        String[] numbersArray = extractNumbers(expression);
        String[] operatorsArray = extractOperators(expression);
        double result = 0;
        for (int i = 0; i < numbersArray.length; i++) {
            result = operate(operatorsArray[i], result, numbersArray[i]);
        }
        return result;
    }

    private String[] correctNumbersArray(String[] extractNumbers) {
        String[] result = Arrays.stream(extractNumbers)
                .filter(x -> x.length() > 0)
                .toArray(String[]::new);
        return result;
    }

    private double operate(String operator, double currentResult, String number) {
        switch (operator) {
            case "":
            case "+":
                return currentResult + Double.parseDouble(number);
            case "-":
                return currentResult - Double.parseDouble(number);
            case "*":
                return currentResult * Double.parseDouble(number);
            case "/":
                return currentResult / Double.parseDouble(number);
//          Два оператори у елементі масиву операторів
            case "+-":
                return currentResult + Double.parseDouble("-" + number);
            case "--":
                return currentResult - Double.parseDouble("-" + number);
            case "*-":
                return currentResult * Double.parseDouble("-" + number);
            case "/-":
                return currentResult / Double.parseDouble("-" + number);
            default:
                return currentResult;
        }
    }

    private String[] extractNumbers(String expression) {
        return correctNumbersArray(expression.split(REGEX_FOR_NUMBERS));
    }

    public int getCountNumbers(String expression) {
        String[] result = extractNumbers(expression);
        return result.length;
    }

    private static String[] extractOperators(String expression) {
        return expression.split(REGEX_FOR_OPERATORS);
    }

    public int getCountOperators(String expression) {
        String[] result = extractOperators(expression);
        return result.length;
    }
}
