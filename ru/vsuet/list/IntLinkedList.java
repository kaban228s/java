package ru.vsuet.list;

import java.util.function.Predicate;

public class IntLinkedList implements IntList {
    private Node head;
    private int size;


    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    @Override
    public int get(int idx) {
        if (idx < 0 || idx >  size){
            throw new IndexOutOfBoundsException();
        }
        Node current = head;
        for (int i = 0; i < idx; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public void add(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;

    }

    @Override
    public void add(int idx, int value) {

        if (idx == 0) {
            Node newNode = new Node(value);
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < idx - 1; i++) {
                current = current.next;
            }
            Node newNode = new Node(value);
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;

    }

    @Override
    public void remove(int idx) {


        if (idx == 0) {
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < idx - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;

    }

    @Override
    public void remove(Predicate<Integer> condition) {
        Node current = head;
        Node prev = null;

        while (current != null) {
            if (condition.test(current.data)) {
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
            } else {
                prev = current;
            }
            current = current.next;
        }

    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        Node current = head;
        while (current != null) {
            builder.append(current.data);
            if (current.next != null) {
                builder.append(", ");
            }
            current = current.next;
        }

        return builder.toString();
    }

}