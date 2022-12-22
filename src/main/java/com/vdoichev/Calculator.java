package com.vdoichev;

import java.util.Arrays;

public class Calculator {

    private static final String REGEX_FOR_NUMBERS = "[^\\d.]+";
    private static final String REGEX_FOR_OPERATORS = "[^-+/*]+";
    private int countNumbers = 0;
    private int countOperators = 0;

    public int getCountNumbers() {
        return countNumbers;
    }

    public void setCountNumbers(int countNumbers) {
        this.countNumbers = countNumbers;
    }

    public int getCountOperators() {
        return countOperators;
    }

    public void setCountOperators(int countOperators) {
        this.countOperators = countOperators;
    }

    public double calculate(String expression) {
        String[] numbersArray = extractNumbers(expression);
        setCountNumbers(numbersArray.length);
        String[] operatorsArray = extractOperators(expression);
        setCountOperators(operatorsArray.length);
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

    private static String[] extractOperators(String expression) {
        return expression.split(REGEX_FOR_OPERATORS);
    }

}
