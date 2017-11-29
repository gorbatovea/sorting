package ru.mail.polis.structures;

import java.util.Comparator;

public class ArrayPriorityQueue<Key>{

    private Key[] elementData;
    private final Comparator<Key> comparator;
    final private int DEFAULT_SIZE;
    private int size = 0;
    private int capacity;
    private long aLong;

    public ArrayPriorityQueue(Comparator<Key> comparator) {
        this.DEFAULT_SIZE = 10;
        this.capacity = this.DEFAULT_SIZE;
        this.comparator = comparator;
        elementData = (Key[]) new Object[this.DEFAULT_SIZE];
        //
    }

    public ArrayPriorityQueue(Key[] data, Comparator<Key> comparator){
        this.comparator = comparator;

        if (data.length > 10) this.DEFAULT_SIZE = data.length;
        else this.DEFAULT_SIZE = 10;
        this.capacity = this.DEFAULT_SIZE;

        this.elementData = (Key[]) new Object[DEFAULT_SIZE];
        for(int i = 0; i < data.length; i++){
            this.elementData[i] = data[i];
            this.size++;
        }
        for(int i = this.size - 1; i >= 0; i--){
            siftDown(i);
        }
    }

    public void update(){
        siftDown(0);
    }

    public void add(Key key) {
        this.size++;
        if (size > capacity) this.grow();
        this.elementData[size - 1] = key;
        if (size > 1) this.siftUp();
    }

    public Key peek() {

        if (size == 0) return null;
        return this.elementData[0];
    }

    public Key extractMin() {

        if (size == 0) return null;

        Key min = this.elementData[0];
        if (size - 1 != 0){
            this.elementData[0] = this.elementData[size-1];
            this.elementData[size - 1] = null;
            this.size--;
            this.siftDown(0);
            if (this.size * 4 == this.capacity) this.shrink();
            return min;
        }
        else{
            size--;
            if (this.size * 4 == this.capacity) this.shrink();
            return min;
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    private void siftUp() {
        int itemNumber = this.size - 1;
        //System.out.println(this.greater((itemNumber - 1) / 2, itemNumber));
        while(this.greater((itemNumber - 1) / 2, itemNumber) && (itemNumber > 0)){
            //swap
            Key temp = this.elementData[itemNumber];
            this.elementData[itemNumber] = this.elementData[(itemNumber - 1) / 2];
            this.elementData[(itemNumber - 1) / 2] = temp;
            itemNumber = (itemNumber - 1) / 2;
        }

    }

    private void swap(int i, int j){
        Key temp = this.elementData[i];
        this.elementData[i] = this.elementData[j];
        this.elementData[j] = temp;
    }

    private void siftDown(int pos) {


        int firstChild = pos * 2 + 1;
        int secondChild = pos * 2 + 2;

        if ((firstChild <= this.size - 1) && (secondChild <= this.size - 1)){
            if (this.greater(firstChild, secondChild)){
                if (this.greater(pos, secondChild)) {
                    this.swap(pos, secondChild);
                    this.siftDown(secondChild);
                }
            }
            else {
                if(this.greater(pos,firstChild)) {
                    this.swap(pos, firstChild);
                    this.siftDown(firstChild);
                }
            }
        }
        else{
            if (firstChild <= this.size - 1)
                if (this.greater(pos, firstChild)){
                    this.swap(pos,firstChild);
                    this.siftDown(firstChild);
                }
                else return;
            if (secondChild <= this.size - 1)
                if (this.greater(pos, secondChild)){
                    this.swap(pos,secondChild);
                    this.siftDown(secondChild);
                }
                else return;
        }
    }

    private void grow() {

        Key[] newArray = (Key[]) new Object[capacity + capacity / 2];
        System.arraycopy(this.elementData, 0, newArray, 0, this.size - 1);
        this.capacity +=capacity / 2;
        this.elementData = newArray;
    }

    private void shrink() {
        Key[] newArray = (Key[]) new Object[this.capacity / 2];
        this.capacity /= 2;
        System.arraycopy(this.elementData, 0, newArray, 0, this.size);
        this.elementData = newArray;
    }

    private boolean greater(int i, int j) {
        //return comparator.compare(elementData[i], elementData[j]) > 0;
        return
                this.comparator.
                        compare(
                                this.elementData[i],
                                this.elementData[j]) > 0;
    }
}
