package com.vdoichev;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Input expression:");
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        Calculator calculator = new Calculator();
        double result = calculator.calculate(expression);
        System.out.println("Result: " + result +
                ", count numbers in expression: " +
                calculator.getCountNumbers(expression));
    }

}
