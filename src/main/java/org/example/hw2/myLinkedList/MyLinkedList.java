package org.example.hw2.myLinkedList;

import org.example.hw2.MyList;

import java.util.Arrays;

public class MyLinkedList<E> implements MyList<E> {
    private int size;
    private MyNode<E> firstNode;
    private MyNode<E> currentNode;
    private MyNode<E> lastNode;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(E element) {
        if (firstNode == null) {
            MyNode<E> node = new MyNode<>(null, element, null);
            firstNode = node;
            lastNode = node;
        } else {
            MyNode<E> node = new MyNode<>(lastNode, element, null);
            lastNode.setNexNode(node);
            lastNode = node;
        }
        size++;
    }

    @Override
    public void add(int index, E element) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " превышает максимальный индекс " + (size - 1));
        }
        currentNode = getNodeByIndex(index);
        MyNode<E> prevNode = currentNode.getPrevNode();
        MyNode<E> node = new MyNode<>(prevNode, element, currentNode);
        prevNode.setNexNode(node);
        currentNode.setPrevNode(node);
        size++;
    }

    @Override
    public E get(int index) {
        return getNodeByIndex(index).getData();
    }

    private MyNode<E> getNodeByIndex(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " превышает максимальный индекс " + (size - 1));
        }
        currentNode = firstNode;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNexNode();
        }
        return currentNode;
    }

    private MyNode<E> getNodeByValue(E element) {
        currentNode = firstNode;
        for (int i = 0; i < size; i++) {
            if (currentNode.getData().equals(element)) {
                return currentNode;
            }
            currentNode = currentNode.getNexNode();
        }
        return null;
    }

    @Override
    public int indexOf(E element) {
        currentNode = firstNode;
        for (int i = 0; i < size; i++) {
            if (currentNode.getData().equals(element)) {
                return i;
            }
            currentNode = currentNode.getNexNode();
        }
        return -1;
    }

    @Override
    public void replace(int index, E element) {
        currentNode = getNodeByIndex(index);
        currentNode.setData(element);
    }

    @Override
    public void removeByIndex(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " превышает максимальный индекс " + (size - 1));
        }
        currentNode = getNodeByIndex(index);
        removeByNode(currentNode);
    }

    @Override
    public void remove(E element) {
        currentNode = getNodeByValue(element);
        if (currentNode != null) {
            removeByNode(currentNode);
        }
    }

    private void removeByNode(MyNode<E> node) {
        MyNode<E> prevNode = node.getPrevNode();
        MyNode<E> nextNode = node.getNexNode();
        prevNode.setNexNode(nextNode);
        nextNode.setPrevNode(prevNode);
        size--;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.currentNode = null;
        this.firstNode = null;
        this.lastNode = null;
    }

    @Override
    public String toString() {
        E[] arr = (E[]) new Object[size];
        currentNode = firstNode;
        for (int i = 0; i < size; i++) {
            arr[i] = currentNode.getData();
            currentNode = currentNode.getNexNode();
        }
        return Arrays.toString(arr);
    }
}
