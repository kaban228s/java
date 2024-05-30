package ru.vsuet.calc;

public class CalculatorController {
    private final CalculatorModel model;
    private final CalculatorView view;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        while (true) {
            String expression = view.getInput();
            if (expression.equalsIgnoreCase("exit")) break;

            try {
                double result = model.calc(expression);
                view.displayResult(result);
            } catch (Exception e) {
                view.displayError(e.getMessage());
            }
        }
    }
}