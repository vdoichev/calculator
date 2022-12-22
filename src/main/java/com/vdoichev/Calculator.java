package com.vdoichev;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {

    private static final String REGEX_FOR_NUMBERS = "[^\\d.]+";
    private static final String REGEX_FOR_OPERATORS = "[^-+/*]+";
    private int countNumbers = 0;
    private int countOperators = 0;
    private List<String> numbersList;
    private List<String> operatorsList;

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
        transformExpressionToLists(expression);

        double result = 0;
        for (int i = 0; i < numbersList.size(); i++) {
            result = operate(operatorsList.get(i), result, numbersList.get(i));
        }

        return result;
    }

    private void transformExpressionToLists(String expression){
        numbersList = extractNumbers(expression);
        setCountNumbers(numbersList.size());
        operatorsList = extractOperators(expression);
        setCountOperators(operatorsList.size());
    }

    private List<String> correctNumbersArray(String[] extractNumbers) {
        return Arrays.stream(extractNumbers)
                .filter(x -> x.length() > 0)
                .collect(Collectors.toList());
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

    private List<String> extractNumbers(String expression) {
        return correctNumbersArray(expression.split(REGEX_FOR_NUMBERS));
    }

    private static List<String> extractOperators(String expression) {
        return Arrays.asList(expression.split(REGEX_FOR_OPERATORS));
    }

}
