package ru.vsuet.matr;


import java.util.Random;

public class Matrix {
    private final int[][] matrix;

    public Matrix(int size) {
        matrix = new int[size][size];
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = rand.nextInt(9) + 1;
            }
        }
    }

    public int sum() {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    public int raznost() {
        int raznost = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                raznost -= matrix[i][j];
            }
        }
        return raznost;
    }

    public int mult() {
        int mult = 1;
        for (int i = 0; i < matrix.length; i++) {
            mult *= matrix[i][i];
        }
        return mult;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Матрица:\n");
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                builder.append(ints[j]).append(" ");
            }
            builder.append("\n");

        }
        return builder.toString();
    }
}
