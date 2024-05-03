package ru.vsuet.matr;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(3);
        System.out.println(matrix.soutMatrix());

        System.out.println("Сумма элементов верхнего треугольника: " + matrix.sum());
        System.out.println("Разность элементов нижнего треугольника: " + matrix.raznost());
        System.out.println("Произведение элементов главной диагонали: " + matrix.mult());
    }
}

