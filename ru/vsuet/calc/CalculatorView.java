package ru.vsuet.calc;

import java.util.Scanner;

public class CalculatorView {
    private Scanner scanner;

    public CalculatorView() {
        scanner = new Scanner(System.in);
    }

    public String getInput() {
        System.out.println("Введите математическое выражение:");
        System.out.println("- Оно должно начинаться и заканчиваться числом.");
        System.out.println("- Допускаются операции: +, -, *, /, %, ^, //, скобки.");
        System.out.println("- Не более 100 элементов.");
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