
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        int [][] arr = new int[3][3];

        for (int i = 0; i < 3; i++ ) {
            for (int j = 0; j < 3; j++ )  {
                arr[i][j] = random.nextInt(9)+1;
            }
        }
        System.out.println(toString(arr));
    }

    static String toString(int[][] arr) {
        return Arrays.deepToString(arr)
                .replace("[[", "|")
                .replace("],", "|")
                .replace("]]", "|")
                .replace(" [", "\n|");
    }
}
