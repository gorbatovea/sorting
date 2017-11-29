package ru.mail.polis.structures;

public class Node<T> {
    public final T value;
    public Node<T> next;
    public Node<T> previous;

    public Node(T obj) {
        this.value = obj;
        this.next = null;
        this.previous = null;
    }
}