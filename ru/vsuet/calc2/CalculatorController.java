package ru.vsuet.calc2;

public class CalculatorController {
    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        while (true) {
            String expression = view.getInput();
            if (expression.equalsIgnoreCase("exit")) break;

            try {
                double result = model.calculate(expression);
                view.displayResult(result);
            } catch (IllegalArgumentException e) {
                view.displayError(e.getMessage());
            }
        }
    }
}