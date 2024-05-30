package ru.vsuet.laba2;

import java.util.Stack;

public class CalculatorModel {
    public double calculate(String expression) {
        expression = expression.trim().replaceAll("\\s+", "");

        if (!areBracketsBalanced(expression)) {
            throw new IllegalArgumentException("Неверное количество скобок в выражении.");
        }

        if (expression.split("[+\\-]").length > 15) {
            throw new IllegalArgumentException("Превышено количество слагаемых (максимум 15).");
        }

        try {
            return evaluate(expression);
        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка в выражении: " + expression);
        }
    }

    private boolean areBracketsBalanced(String expr) {
        Stack<Character> stack = new Stack<>();
        for (char c : expr.toCharArray()) {
            if (c == '(') stack.push(c);
            else if (c == ')') {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    private double evaluate(String expression) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() { ch = (++pos < expression.length()) ? expression.charAt(pos) : -1; }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length())
                    throw new RuntimeException("Неожиданный символ: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                while (true) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parsePower();
                while (true) {
                    if (eat('*')) x *= parsePower();
                    else if (eat('/')) {
                        if (ch == '/') {
                            nextChar();
                            x = Math.floor(x / parsePower());
                        } else {
                            x /= parsePower();
                        }
                    }
                    else if (eat('%')) x %= parsePower();
                    else return x;
                }
            }
            double parsePower() {
                double x = parseFactor();
                while (eat('^')) {
                    x = Math.pow(x, parseFactor());
                }
                return x;
            }
            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;

                if (eat('(')) {
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Пропущена закрывающая скобка");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else if (expression.startsWith("log(", pos)) {
                    pos += 3;
                    nextChar();
                    x = Math.log(parseExpression()) / Math.log(2);
                    if (!eat(')')) throw new RuntimeException("Ожидается закрытие log()");
                } else if (expression.startsWith("exp(", pos)) {
                    pos += 3;
                    nextChar();
                    x = Math.exp(parseExpression());
                    if (!eat(')')) throw new RuntimeException("Ожидается закрытие exp()");
                } else {
                    throw new RuntimeException("Неожиданный символ: " + (char) ch);
                }

                while (eat('!')) {
                    x = factorial((int) x);
                }
                return x;
            }

            long factorial(int n) {
                if (n < 0) throw new RuntimeException("Факториал от отрицательного числа");
                long result = 1;
                for (int i = 2; i <= n; i++) result *= i;
                return result;
            }
        }.parse();
    }
}