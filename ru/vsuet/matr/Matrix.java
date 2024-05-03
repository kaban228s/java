package ru.vsuet.matr;


import java.util.Random;

public class Matrix {
    private final int[][] matrix;

    public Matrix(int size) {
        matrix = new int[size][size];
        Random rand = new Random();


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = rand.nextInt(9) + 1;
            }
        }
    }

    public int sum() {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 3; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    public int raznost() {
        int raznost = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < i; j++) {
                raznost -= matrix[i][j];
            }
        }
        return raznost;
    }

    public int mult() {
        int mult = 1;
        for (int i = 0; i < 3; i++) {
            mult *= matrix[i][i];
        }
        return mult;
    }

    public String soutMatrix() {
        StringBuilder builder = new StringBuilder();
        builder.append("Матрица:\n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                builder.append(matrix[i][j]).append(" ");
            }
            builder.append("\n");

        }return builder.toString();
    }
}
