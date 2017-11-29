package ru.mail.polis.structures;

public class SomeIntKeyObject<T> implements IntKeyObject<T>{
    public final int key;
    public final T value;

    public SomeIntKeyObject(int key,T value){
        this.key = key;
        this.value = value;
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public int compareTo(IntKeyObject anotherIntKeyObject) {
        return Integer.compare(this.getKey(), anotherIntKeyObject.getKey());
    }
}
