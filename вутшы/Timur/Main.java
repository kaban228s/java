package вутшы.Timur;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyHashTable hashTable = new MyHashTable(10);

        hashTable.put("1", "Процессор");
        hashTable.put("2", "Видеокарта");
        hashTable.put("3", "Материнская плата");
        hashTable.put("4", "Оперативная память");
        hashTable.put("5", "Жесткий диск");
        hashTable.put("6", "Блок питания");
        hashTable.put("7", "Кулер");
        hashTable.remove("1");
        System.out.println(Arrays.toString(hashTable.keys()));
        hashTable.display();
    }
}