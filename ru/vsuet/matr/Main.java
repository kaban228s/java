package ru.vsuet.matr;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter matrix size: ");
        int size = sc.nextInt();
        Matrix matrix = new Matrix(size);
        System.out.println(matrix);

        System.out.println("Сумма элементов верхнего треугольника: " + matrix.sum());
        System.out.println("Разность элементов нижнего треугольника: " + matrix.raznost());
        System.out.println("Произведение элементов главной диагонали: " + matrix.mult());
    }
}

