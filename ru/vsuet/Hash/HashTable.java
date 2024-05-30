package ru.vsuet.Hash;

import java.nio.charset.StandardCharsets;
import java.util.function.BiConsumer;

public class HashTable<T> {

    private static class Node<T> {
        public String key;
        public T value;

        public Node(String key, T value) {
            this.key = key;
            this.value = value;
        }
    }

    public int hash(String key) {
        int hash = 5381;
        byte[] ascii = key.getBytes(StandardCharsets.US_ASCII);
        for (byte bit : ascii) {
            hash = 17 * hash * bit + bit;
        }
        return Math.abs(hash % table.length);
    }

    private final TLinkedList<Node<T>>[] table;

    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        table = new TLinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new TLinkedList<>();
        }
    }

    public HashTable() {
        this(128);
    }

    public void add(String key, T value) {
        action(key, (node, bucket) -> {
            if (node != null) {
                node.value = value;
            } else {
                bucket.add(new Node<>(key, value));
            }
        });
    }

    public void remove(String key) {
        action(key, (node, bucket) -> {
            if (node != null) {
                for (int i = 0; i < bucket.size(); i++) {
                    if (bucket.get(i).key.equals(key)) {
                        bucket.remove(i);
                        break;
                    }
                }
            }
        });
    }

    public T get(String key) {
        Node<T> node = action(key, (a, b) -> {});
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Node<T> action(String key, BiConsumer<Node<T>, TLinkedList<Node<T>>> action) {
        int idx = hash(key);
        TLinkedList<Node<T>> bucket = table[idx];
        for (int i = 0; i < bucket.size(); i++) {
            Node<T> node = bucket.get(i);
            if (node.key.equals(key)) {
                action.accept(node, bucket);
                return node;
            }
        }
        action.accept(null, bucket);
        return null;
    }
}
