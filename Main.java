import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите размерность матрицы: ");
        int size = input.nextInt();
        int[][] arr = new int[size][size];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = rand.nextInt(1,10 - 1);
            }
        }

        System.out.println(Arrays.deepToString(arr)
                .replace("[[","|")
                .replace("]]","|")
                .replace(" [","|")
                .replace("],","|\n")
        );

        int diag = 1;
        int top = 0;
        int bot = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i == j) {
                    diag *= arr[i][j];
                } else if (i < j) {
                    top += arr[i][j];
                } else if (i > j) {
                    bot -= arr[i][j];
                }
            }
        }

        System.out.println("Произведение диагонали: " + diag);
        System.out.println("Сумма верха: " + top);
        System.out.println("Разность низа: " + bot);
    }
}