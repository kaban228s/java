package ru.vsuet.hashmodel;

public class NoteBookProbe {
    public static void main(String[] args) {
        HashTable<String> notebook = new HashTable<>();
        notebook.add("27.05.24", "Сделать тест по лин. алгебре");
        notebook.add("31.05.24", "Приехать домой");
        notebook.add("02.06.24", "Приехать в Воронеж");

        System.out.println(notebook.get("27.05.24"));
        System.out.println(notebook.get("31.05.24"));
        System.out.println(notebook.get("02.06.24"));

        notebook.del("27.05.24");
        notebook.del("02.06.24");
        notebook.add("31.05.24", "Купить билет");

        System.out.println(notebook.get("27.05.24"));
        System.out.println(notebook.get("02.06.24"));
        System.out.println(notebook.get("31.05.24"));
    }
}
