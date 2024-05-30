package ru.vsuet.calc2;

public class CalculatorModel {
    public double calculate(String expression) throws IllegalArgumentException {
        expression = expression.replaceAll("\\s+", ""); // Удаляем пробелы

        // Проверка на корректные символы
        if (!expression.matches("[0-9+\\-*/.]++")) {
            throw new IllegalArgumentException("Недопустимые символы в выражении");
        }

        // Разбиваем выражение на числа и операторы
        String[] tokens = expression.split("(?<=[+\\-*/])|(?=[+\\-*/])");

        // Обработка умножения и деления
        for (int i = 1; i < tokens.length; i += 2) {
            if (tokens[i].equals("*") || tokens[i].equals("/")) {
                double left = Double.parseDouble(tokens[i - 1]);
                double right = Double.parseDouble(tokens[i + 1]);
                double result = tokens[i].equals("*") ? left * right : left / right;

                // Обновляем массив токенов
                tokens[i + 1] = String.valueOf(result);
                tokens[i - 1] = null;
                tokens[i] = null;
            }
        }

        // Обработка сложения и вычитания
        double total = 0;
        String lastOp = "+";
        for (String token : tokens) {
            if (token == null) continue;

            if ("+-".contains(token)) {
                lastOp = token;
            } else {
                double num = Double.parseDouble(token);
                total = switch (lastOp) {
                    case "+" -> total + num;
                    case "-" -> total - num;
                    default -> total;
                };
            }
        }

        return total;
    }
}