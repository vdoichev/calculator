package com.vdoichev;

public class Calculator {

    public double calculate(String expression) {
        String[] numbersArray = extractNumbers(expression);
        String[] operatorsArray = extractOperators(expression);
        double result = 0;
        for (int i = 0; i < numbersArray.length; i++) {
            result = operate(operatorsArray[i], result, numbersArray[i]);
        }

        return result;
    }

    private double operate(String operator, double currentResult, String number) {
        switch (operator) {
            case "":
            case "+":
                return currentResult + Double.parseDouble(number);
            case "-":
                return currentResult - Double.parseDouble(number);
        }
        return currentResult;
    }

    private String[] extractNumbers(String expression) {
        String regex = "[^\\d]+";
        return expression.split(regex);
    }

    public int getCountNumbers(String expression) {
        String[] result = extractNumbers(expression);
        return result.length;
    }

    private static String[] extractOperators(String expression) {
        String regex = "[^-+/*]+";
        return expression.split(regex);
    }

    public int getCountOperators(String expression) {
        String[] result = extractOperators(expression);
        return result.length;
    }
}
