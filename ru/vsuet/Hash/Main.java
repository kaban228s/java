package ru.vsuet.Hash;

public class Main {
    public static void main(String[] args) {
        HashTable<String> id = new HashTable<>();

        id.add("1", "111");
        id.add("2", "222");
        id.add("3", "333");
        id.add("4", "444");
        id.add("5", "555");
        id.add("6", "666");
        id.add("7", "777");
        id.add("8", "888");
        id.add("9", "999");

        System.out.println(id.get("1"));
        System.out.println(id.get("2"));
        System.out.println(id.get("3"));
        System.out.println(id.get("4"));
        System.out.println(id.get("5"));
        System.out.println(id.get("6"));
        System.out.println(id.get("7"));
        System.out.println(id.get("8"));
        System.out.println(id.get("9"));

        id.remove("4");
        System.out.println(id.get("4"));

    }
}
