package org.example.hw2.myLinkedList;

public class MyNode<E> {

    private MyNode<E> prevNode;
    private E data;
    private MyNode<E> nexNode;

    public MyNode(MyNode<E> prevNode, E data, MyNode<E> nexNode) {
        this.prevNode = prevNode;
        this.data = data;
        this.nexNode = nexNode;
    }

    public MyNode<E> getPrevNode() {
        return prevNode;
    }

    public E getData() {
        return data;
    }

    public MyNode<E> getNexNode() {
        return nexNode;
    }

    public void setPrevNode(MyNode<E> prevNode) {
        this.prevNode = prevNode;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void setNexNode(MyNode<E> nexNode) {
        this.nexNode = nexNode;
    }
}
