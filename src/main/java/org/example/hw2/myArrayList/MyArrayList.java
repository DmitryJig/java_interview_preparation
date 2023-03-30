package org.example.hw2.myArrayList;

import org.example.hw2.MyList;

import java.util.Arrays;

public class MyArrayList<E> implements MyList<E> {
    private int size;
    private final int INITIAL_CAPACITY = 10;
    private E[] array;

    public MyArrayList() {
        this.array = (E[]) new Object[INITIAL_CAPACITY]; // не дает new E[16]
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(E element) {
        if (size >= array.length - 1) {
            increaseArray();
        }
        array[size] = element;
        size++;
    }

    @Override
    public void add(int index, E element) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " превышает длинну массива " + (size - 1));
        }
        size++;
        if (size >= array.length - 1) {
            increaseArray();
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = element;
    }

    @Override
    public E get(int index) {
        if (index < size) {
            return array[index];
        } else {
            throw new IndexOutOfBoundsException("Индекс " + index + " превышает длинну массива " + (size - 1));
        }
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void replace(int index, E element) {
        this.array[index] = element;
    }

    @Override
    public void removeByIndex(int index) {
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    @Override
    public void remove(E element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                removeByIndex(i);
            }
        }
    }

    @Override
    public void clear() {
        this.array = (E[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    private void increaseArray() {
        int oldLength = array.length;
        int newLength = size * 2;
        E[] newArray = (E[]) new Object[newLength];
        for (int i = 0; i < oldLength; i++) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(array, size));
    }
}
