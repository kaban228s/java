package ru.vsuet.list;

import java.util.function.Predicate;

public interface IntList {

    int get(int idx);
    void add(int value);
    void add(int idx, int value);
    void remove(int idx);
    void remove(Predicate<Integer> condition);
    int size();
}
