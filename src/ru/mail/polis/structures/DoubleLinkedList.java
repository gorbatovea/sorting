package ru.mail.polis.structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class DoubleLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    private int modCount = 0;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addFront(T obj){
        this.modCount++;
        if (this.size == 0) {
            this.head = new Node<>(obj);
            this.tail = head;
            this.size++;
        }else{
            Node newNode = new Node(obj);
            this.head.previous = newNode;
            newNode.next = head;
            this.head = newNode;
            this.size++;
        }
    }

    public void addBack(T obj) {
        this.modCount++;
        if (this.size == 0) {
            this.head = new Node<>(obj);
            this.tail = head;
            this.size++;
        } else {
            Node<T> newNode = new Node<>(obj);
            this.tail.next = newNode;
            newNode.previous = this.tail;
            this.tail = newNode;
            this.size++;
        }
    }

    public void deleteLast() {
        if (this.size == 0) throw new ArrayIndexOutOfBoundsException();
        this.modCount++;
        Node current = this.head;
        if (this.head.next == null) {
            this.head = null;
            this.tail = null;
            this.size = 0;
        } else {
            this.tail = tail.previous;
            this.tail.next.previous = null;
            this.tail.next = null;
            this.size--;
        }

    }

    public void deleteFirst() {
        if (this.size == 0) throw new ArrayIndexOutOfBoundsException();
        this.modCount++;
        if (this.head.next == null) {
            this.head = null;
            this.tail = null;
            this.size = 0;
        } else {
            this.head = this.head.next;
            this.head.previous.next = null;
            this.head.previous = null;
            this.size--;
        }

    }

    public T getLastValue() {
        if (this.size == 0) throw new ArrayIndexOutOfBoundsException();
        else return tail.value;
    }

    public Node<T> getHead(){
        if (this.size == 0) throw new ArrayIndexOutOfBoundsException();
        return this.head;
    }

    public Node<T> getTail(){
        if (this.size == 0) throw new ArrayIndexOutOfBoundsException();
        return this.tail;
    }

    public void insert(int index, T obj){
        if (this.size == 0) {
            this.addFront(obj);
        }
        if (index == 0){
            Node<T> newNode = new Node<>(obj);
            newNode.next = this.head;
            head.previous = newNode;
            this.head = newNode;
            this.size++;
            return;
        }
        if (index == this.size){
            Node<T> newNode = new Node<>(obj);
            newNode.previous = this.tail;
            tail.next = newNode;
            this.tail = newNode;
            this.size++;
            return;
        }
        if ((index > 0) && (index < this.size)){
            Node<T> newNode = new Node<>(obj);
            Node<T> currentNode = this.get(index);
            newNode.next = currentNode;
            newNode.previous = currentNode.previous;
            currentNode.previous.next = newNode;
            currentNode.previous = newNode;
            this.size++;
        }
        else throw new ArrayIndexOutOfBoundsException();
    }

    public int size() {
        return this.size;
    }

    public Node<T> get(int index){
        if ((index >= this.size) || (index < 0)) throw new ArrayIndexOutOfBoundsException();
        int i = 0;
        Node temp = head;
        while(i < index){
            i++;
            temp = temp.next;
        }
        return temp;
    }

    public T getValue(int index){
        if ((index >= this.size) || (index < 0)) throw new ArrayIndexOutOfBoundsException();
        int i = 0;
        Node temp = head;
        while(i < index){
            i++;
            temp = temp.next;
        }
        return (T)temp.value;
    }

    /*private class Node<T> {
        public T value;
        public Node<T> next;
        public Node<T> previous;

        Node(T obj) {
            this.value = obj;
            this.next = null;
            this.previous = null;
        }
    }*/

    public Iterator<T> iterator(){
        return new DoubleLinkedListIterator();
    }

    private class DoubleLinkedListIterator implements Iterator<T>{

        private Node<T> current = head;
        private final int state = modCount;

        @Override
        public boolean hasNext() {
            if (this.state != modCount) throw new ConcurrentModificationException();
            return current != null;
        }

        @Override
        public T next() {
            if (this.state != modCount) throw new ConcurrentModificationException();
            if (this.hasNext()){
                T item = current.value;
                current = current.next;
                return item;
            }
            return null;
        }
    }
}