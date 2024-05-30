package ru.vsuet.laba2;

import java.util.Scanner;

import java.util.Scanner;

public class CalculatorView {
    private Scanner scanner;

    public CalculatorView() {
        scanner = new Scanner(System.in);
    }

    public String getInput() {
        System.out.println("Введите математическое выражение:");
        System.out.println("- Поддерживаются: +, -, *, /, ^, **, //");
        System.out.println("- Скобки (), логарифм log(), экспонента exp(), факториал !");
        System.out.println("- Макс. 15 слагаемых. Для выхода введите: exit");
        System.out.print("> ");
        return scanner.nextLine();
    }

    public void displayResult(double result) {
        System.out.println("Результат: " + result);
    }

    public void displayError(String message) {
        System.out.println("Ошибка: " + message);
    }
}