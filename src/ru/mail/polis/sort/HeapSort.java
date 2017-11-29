package ru.mail.polis.sort;

import ru.mail.polis.structures.ArrayPriorityQueue;
import java.util.Comparator;

public class HeapSort<T> extends AbstractSortOnComparisons<T> implements Sort<T>{
    private final Comparator<T> comparator = super::compare;
    public HeapSort(){
        super();
    }

    public HeapSort(Comparator<? super T> comparator){
        super();
        setComparator(comparator);
    }

    public void sort(T[] list){
        ArrayPriorityQueue<T> heap = new ArrayPriorityQueue<>(list, comparator);
        for(int i = 0; i < list.length; i++)
            list[i] = heap.extractMin();
    }
}
