package ru.mail.polis.sort;

import ru.mail.polis.structures.IntKeyObject;


public class CountingSort<T extends IntKeyObject> extends AbstractSortOnComparisons<T> implements Sort<T>{

    public CountingSort(){
        super();
    }

    public void sort(T[] list) {
        int maxValue = 1;
        for (T aList : list)
            if (aList.getKey() > maxValue) {
                maxValue = aList.getKey();
            }
        int[] count = new int[maxValue + 1];

        for (T aList : list) {
            count[aList.getKey()]++;
        }
        int[] objectsPositions = new int[count.length];
        int lastPosition = 0;
        for(int i = 0; i < count.length; i++){
            if (count[i] != 0) lastPosition += count[i];
            objectsPositions[i] = lastPosition - 1;
        }
        T[] sortedList = (T[])new IntKeyObject[list.length];
        for(int i = list.length - 1; i >= 0; i--){
            sortedList[objectsPositions[list[i].getKey()]--] = list[i];
        }
        System.arraycopy(sortedList, 0, list, 0, list.length);
    }
}
