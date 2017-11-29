package ru.mail.polis.sort;

import java.util.Comparator;

public class QuickSort<T> extends AbstractSortOnComparisons<T>{
    private final int TRASH_HOLD = 15;
    private final InsertionSort<T> nestedSort;

    public QuickSort(){
        super();
        nestedSort = new InsertionSort<>();
    }

    public QuickSort(Comparator<? super T> comparator){
        super();
        setComparator(comparator);
        nestedSort = new InsertionSort<>(comparator);
    }

    public void sort(T[] list){
        quickSort(list, 0, list.length - 1);
    }

    private void quickSort(T[] list, int left, int right){
        if (left >= right) return;
        int idx = partition(list, left, right);
        int size = right - left + 1;
        if (size >= TRASH_HOLD) {
            //Call insert sort
            nestedSort.sort(list);
            return;
        }
        quickSort(list, left, idx);
        quickSort(list, idx + 1, right);
    }

    private int partition(T[] list, int left, int right){
        T p = list[left + (int)(Math.random() * (right - left))];//left + (right - left + 1) / 2
        int i = left, j = right;
        while (i <= j){
            while (lesser(list[i], p)) i++;
            while (greater(list[j], p)) j--;
            if (i <= j) swap (list, i++, j--);
        }
        return j;
    }
}
