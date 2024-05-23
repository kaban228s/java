package ru.vsuet.hashtable;

import ru.vsuet.list.IntLinkedList;


import java.nio.charset.StandardCharsets;
//import java.util.IntLinkedList;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

import static java.lang.Math.abs;

public class HashTable {

    private static class Node {
        public int key;
        public int value;

        public Node(int key, int value) {
            this.key = Integer.parseInt(key);

            this.value = value;
        }
    }

    public int hash(String key) {
        int hash = 5381;
        byte[] ASCII = key.getBytes(StandardCharsets.US_ASCII);
        for (byte bit : ASCII) {
            hash = 17 * hash + bit;
        }
        return hash % table.length;

    }



    private final IntLinkedList[] table;

    public HashTable(int size) {
        table = new IntLinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new IntLinkedList<>();
        }
    }

    public HashTable() {
        table = new IntLinkedList[128];
        for (int i = 0; i < 128; i++) {
            table[i] = new IntLinkedList<>();
        }
    }

    public void add(int key, int value) {
        action(String.valueOf(key), (node, bucket) -> {
            if (node != null) {
                node.value = value;
            }
            bucket.add(new Node(key, value));
        });
    }

    public void del(int key) {
        action(String.valueOf(key), (node, bucket) -> {
            if (node != null) {
                bucket.remove((Predicate<Integer>) node);
            }
        });
    }

    public Object get(String key) {
        Object2 node = action(key, (a, b) -> {});
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Object2 action(String key, BiConsumer<Integer, IntLinkedList<Node>> actions) {
        int idx = hash(key);
        IntLinkedList<Node> bucket = table[idx];
        for (int i = 0; i < bucket.size(); i++) {
            int node = bucket.get(i);
            if (!node.equals(key)) {
                continue;
            }
            actions.accept(node, bucket);
            return node;
        }
        actions.accept(null, bucket);
        return null;
    }
}