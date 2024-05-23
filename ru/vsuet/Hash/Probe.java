package ru.vsuet.Hash;


import java.util.Random;
import java.util.function.Consumer;

public class Probe {

    public static String generateRandomString(int length) {
        String characters = "qwertyuiopasdfghjklzxcvbnm";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }

    public static class Key {

        public String key;

        public Key(String key) {
            this.key = key;
        }

        public void grs(int len) {
            this.key = generateRandomString(len);
        }

        public void rng() {
            Random rn = new Random();
            this.key = String.valueOf(rn.nextInt());
        }

        @Override
        public String toString() {
            return key;
        }
    }

    public static int CollisionCheck(Consumer<Key> rng, int count) {
        HashTable<String> table = new HashTable<>(4196);
        Key key = new Key("`");
        int collision_count = 0;
        int latest_hash = 0;
        for (int i = 0; i < count; i++) {
            rng.accept(key);
            int hash = table.hash(key.toString());
            if (latest_hash == hash) {
                collision_count++;
            };
//            System.out.println(key + " | " + hash);
            latest_hash = hash;
        }
        return collision_count;
    }

    public static void main(String[] args) {
        Consumer<Key> randomString_100 = key -> key.grs(100);
        Consumer<Key> randomString_10 = key -> key.grs(10);
        Consumer<Key> randomString_3 = key -> key.grs(3);
        Consumer<Key> randomString_2 = key -> key.grs(2);
        Consumer<Key> randomInt= Key::rng;
        System.out.println("Кол-во коллизий (Строка 100 символов): " + CollisionCheck(randomString_100, 128));
        System.out.println("Кол-во коллизий (Строка 10 символов): " + CollisionCheck(randomString_10, 128));
        System.out.println("Кол-во коллизий (Строка 3 символов): " + CollisionCheck(randomString_3, 128));
        System.out.println("Кол-во коллизий (Строка 2 символов): " + CollisionCheck(randomString_2, 128));
        System.out.println("Кол-во коллизий (Рандомное число): " + CollisionCheck(randomInt, 128));
    }
}