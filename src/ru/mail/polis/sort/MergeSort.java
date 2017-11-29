package ru.mail.polis.sort;

import java.util.Comparator;

public class MergeSort <T> extends AbstractSortOnComparisons<T> implements Sort<T>{

    public MergeSort(){
        super();
    }

    public MergeSort(Comparator<? super T> comparator){
        super();
        setComparator(comparator);
    }

    public void sort(T[] list) {
        nestedSort(list,0, list.length - 1);
    }

    private void nestedSort(T[] list, int start, int end){
        if (start == end) return;
        int size = (end - start +  1);
        int pivot =start +  size / 2;
        nestedSort(list, start, pivot - 1);
        nestedSort(list, pivot, end);
        int indexLeft = start, indexRight = pivot, mergedIndex = 0;
        T mergedList[] = (T[]) new Object[size];
        while ((indexLeft < pivot)&&(indexRight <= end)){
            if (lesser(list[indexLeft], list[indexRight])){
                mergedList[mergedIndex++] = list[indexLeft++];
            }
            else{
                mergedList[mergedIndex++] = list[indexRight++];
            }
        }
        if (indexLeft < pivot)
            while(indexLeft < pivot){
                mergedList[mergedIndex++] = list[indexLeft++];
            }
        if (indexRight <= end)
            while(indexRight <= end){
                mergedList[mergedIndex++] = list[indexRight++];
            }
        int index = start;
        for (T aMergedList : mergedList)
            list[index++] = aMergedList;
    }
}
