package вутшы;

import java.util.Scanner;

public class Pluh {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("сколько ты весишь ");
        int weight = scanner.nextInt();
        if ( weight > 112){
            System.out.println("Ты очень жирный Денис ");
        }else {
            System.out.println("Всё нормально ты не денис ");
        }

    }
}
