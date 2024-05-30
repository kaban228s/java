package ru.vsuet.ss;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Введите основание системы счисления (например, 5): ");
        int baseSystem = scanner.nextInt();
        System.out.print("Введите правильное число для перевода ");
        int num = scanner.nextInt();

        int decimal = anyToDecimal(num, baseSystem);

        System.out.println("Десятичное: " + decimal);

        String hex = decimalToHex(decimal);
        System.out.println("Шестнадцатеричное: " + hex);

        scanner.close();
    }


    public static int anyToDecimal(int num, int baseSystem) {
        int decimal = 0, base = 1;
        while (num > 0) {
            int lastDigit = num % 10;
            decimal += lastDigit * base;
            base *= baseSystem;
            num /= 10;
        }
        return decimal;
    }


    public static String decimalToHex(int num) {
        String hexChars = "0123456789ABCDEF";
        StringBuilder hex = new StringBuilder();
        while (num > 0) {
            hex.insert(0, hexChars.charAt(num % 16));
            num /= 16;
        }
        return (hex.isEmpty()) ? "0" : hex.toString();
    }
}