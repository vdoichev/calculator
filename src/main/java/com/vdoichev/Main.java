package com.vdoichev;

import com.vdoichev.db.DBCalculator;
import com.vdoichev.utils.Calculator;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DBCalculator dbCalculator = new DBCalculator();
    private static final Calculator calculator = new Calculator();

    public static void main(String[] args) {
        try {
            startDialogWithUser();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void startDialogWithUser() throws InterruptedException {
        String command;
        System.out.println("Hello, user!");

        do {
            command = printQuestion();
            switch (command) {
                case "calculate": {
                    calculateNewExpression();
                    break;
                }
                case "all": {
                    dbCalculator.findAll();
                    break;
                }
                case "find": {
                    findByResult();
                    break;
                }
                case "update": {
                    updateExpression();
                    break;
                }
                case "exit": {
                    System.out.println("Goodbye, user!");
                    break;
                }
                default:
                    System.out.println("Command not defined!");
                    break;
            }
            if (!command.equals("exit")) {
                Thread.sleep(5000);
                System.out.println("------------------------------------------");
            }
        } while (!command.equals("exit"));
    }

    private static String printQuestion() {
        System.out.println("What action do you want to perform?");
        System.out.println("1. Calculate new expression enter 'calculate'");
        System.out.println("2. Print all expressions enter 'all'");
        System.out.println("3. Find expression by result enter 'find'");
        System.out.println("4. Update expression by id enter 'update'");
        System.out.println("5. To complete work enter 'exit'");
        return scanner.nextLine();
    }

    private static void printResult(String expression, double result, int id, int count) {
        String output = String.format("Expression %s  is valid, result:  %s  save in DB with ID = %s",
                expression, result, id);
        System.out.println(output);
        if (count > 0) {
            System.out.println("Count numbers in expression: " + count);
        }
    }

    private static void calculateNewExpression() {
        System.out.println("Input expression:");
        String expression = scanner.nextLine();
        Double result = calculator.calculate(expression);
        if (result!=null) {
            int id = dbCalculator.addExpression(expression, result);
            printResult(expression, result, id, calculator.getCountNumbersInList());
        }
    }

    private static void updateExpression() {
        System.out.println("Input ID expression for update:");
        String id = scanner.nextLine();
        if (dbCalculator.findById(Integer.parseInt(id))) {
            System.out.println("Input expression:");
            String expression = scanner.nextLine();
            double result = calculator.calculate(expression);
            if (dbCalculator.updateExpression(Integer.parseInt(id), expression, result)) {
                printResult(expression, result, Integer.parseInt(id), calculator.getCountNumbersInList());
            } else {
                System.out.println("An error has occurred!");
            }
        } else {
            System.out.println("Expression by id not found!");
        }
    }

    private static void findByResult() {
        System.out.println("Input condition (example: > or < or =):");
        String condition = scanner.nextLine();
        if (condition.equals("=") || condition.equals(">") || condition.equals("<")) {
            System.out.println("Input value:");
            String value = scanner.nextLine();
            if (dbCalculator.findByResult(condition, value)) {
                System.out.println("Search completed!");
            } else {
                System.out.println("Expression by condition not found!");
            }
        } else {
            System.out.println("Condition is not correct!");
        }
    }
}
