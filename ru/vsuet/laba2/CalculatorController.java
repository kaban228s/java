package ru.vsuet.laba2;

public class CalculatorController {
    private final CalculatorModel model;
    private final CalculatorView view;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        System.out.println("Калькулятор запущен. ");

        while (true) {
            String expression = view.getInput();
            if (expression.equalsIgnoreCase("exit")) {
                System.out.println("Завершение работы калькулятора.");
                break;
            }

            try {
                double result = model.calculate(expression);
                view.displayResult(result);
            } catch (IllegalArgumentException e) {
                view.displayError(e.getMessage());
            } catch (Exception e) {
                view.displayError("Непредвиденная ошибка: " + e.getMessage());
            }
        }
    }
}