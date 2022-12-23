package com.vdoichev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Calculator {
    private static final String REGEX_FOR_VALIDATE = "[^\\d.\\-+/*\\s]";
    private static final String REGEX_FOR_NUMBERS = "[^\\d.]+";
    private static final String REGEX_FOR_OPERATORS = "[^-+/*]+";
    private List<String> operatorsList;
    private int countOperators = 0;
    private List<Double> numbersList;
    private int countNumbers = 0;

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

    private boolean isNotSingleArgument() {
        return numbersList.size() > 1;
    }

    /**
     * **************************************************
     * методи відповідальні за разразхунок
     * **************************************************
     */

    public double calculate(String expression) {
        if (validate(expression)) {
            transformExpressionToLists(expression);
            if (numbersList.size() > 0) {
                while (isNotSingleArgument()) {
                    int i = getIndexByPriority(operatorsList);
                    double result = operate(operatorsList.get(i), numbersList.get(i - 1), numbersList.get(i));
                    numbersList.set(i - 1, result);
                    numbersList.remove(i);
                    operatorsList.remove(i);
                }
            }
            return numbersList.get(0);
        } else {
            System.out.println("Вираз містить заборонені символи!");
            return 0;
        }
    }

    public int getIndexByPriority(List<String> operatorsList) {
        for (int i = 1; i < operatorsList.size(); i++) {
            if (operatorsList.get(i).equals("*") ||
                    operatorsList.get(i).equals("/")) {
                return i;
            }
        }
        for (int i = 1; i < operatorsList.size(); i++) {
            if (operatorsList.get(i).equals("+") ||
                    operatorsList.get(i).equals("-")) {
                return i;
            }
        }

        return 0;
    }

    private double operate(String operator, Double firstNumber, Double secondNumber) {
        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                return firstNumber / secondNumber;
            default:
                return firstNumber;
        }
    }

    /**
     * ***********************************************************
     * методи відповідальні за перетворення виразу у листи
     * ***********************************************************
     **/
    private void transformExpressionToLists(String expression) {
        operatorsList = extractOperators(expression);
        setCountOperators(operatorsList.size());
        numbersList = extractNumbers(expression);
        setCountNumbers(numbersList.size());
    }

    private List<Double> stringListToDoubleList(List<String> numbersStringList) {
        List<Double> doubleList = new ArrayList<>();
        for (int i = 0; i < operatorsList.size(); i++) {
            int length = operatorsList.get(i).length();

            if (length == 2 && operatorsList.get(i).charAt(1) == '-') {
                replaceToNegativeNumber(numbersStringList, i, length);
            }
            if (length == 1 && i == 0 && operatorsList.get(i).charAt(0) == '-') {
                replaceToNegativeNumber(numbersStringList, i, length);
            }
            doubleList.add(Double.parseDouble(numbersStringList.get(i)));
        }
        return doubleList;
    }

    private void replaceToNegativeNumber(List<String> numbersStringList, int index, int operatorLength) {
        String str = "-" + numbersStringList.get(index);
        numbersStringList.set(index, str);
        str = (operatorLength == 2) ? operatorsList.get(index).substring(0, 1) : "";
        operatorsList.set(index, str);
    }

    private List<Double> extractNumbers(String expression) {
        return stringListToDoubleList(correctNumbersArray(expression.split(REGEX_FOR_NUMBERS)));
    }

    private List<String> correctNumbersArray(String[] extractNumbers) {
        return Arrays
                .stream(extractNumbers)
                .filter(x -> x.length() > 0)
                .collect(Collectors.toList());
    }

    private static List<String> extractOperators(String expression) {
        return new ArrayList<>(Arrays.asList(expression.split(REGEX_FOR_OPERATORS)));
    }

    /**
     * ***********************************************************
     * методи відповідальні за валідацію виразу
     * ***********************************************************
     **/

    public boolean validate(String expression) {
        Pattern pattern = Pattern.compile(REGEX_FOR_VALIDATE);
        Matcher matcher = pattern.matcher(expression);
        return !matcher.find();
    }
}
