package ru.mail.polis.sort;

import ru.mail.polis.structures.Numerical;

import java.util.Comparator;

public class LSDSort<T extends Object & Numerical<T>> extends AbstractSortOnComparisons<T> implements Sort<T>{

    public LSDSort(){
        super();
    }

    public LSDSort(Comparator comparator){
        super();
        setComparator(comparator);
    }

    @Override
    public void setComparator(Comparator<? super T> comparator) {
        super.setComparator(comparator);
    }

    @Override
    public void sort(T[] array) {
        int range = findMaxDigitValue(array), maxDigitsAmount = findMaxDigitsAmount(array);
        for(int d = maxDigitsAmount; d >= 1; d--){
            int[] count = new int[range + 1];
            for(T object : array)   count[getDigit(object, d)]++;
            for(int i = 1; i < count.length; i++) count[i] += count[i - 1];
            T[] sortedArray = (T[])new Object[array.length];
            for(int i = sortedArray.length - 1; i >= 0; i--){
                sortedArray[--count[getDigit(array[i], d)]] = array[i];
            }
            System.arraycopy(sortedArray,0, array, 0, array.length);
        }
    }

    private int getDigit(T object, int digit) {
        if (object.getDigitCount() > digit) return object.getDigit(digit - 1);
        return 0;
    }

    private int findMaxDigitsAmount(T[] array) {
        int max = 0;
        for(T x : array){
            if (x.getDigitCount() > max) max = x.getDigitCount();
        }
        return max;
    }

    private int findMaxDigitValue(T[] array) {
        int max = 0;
        for (T anArray : array)
            if (anArray.getDigitMaxValue() > max)
                max = anArray.getDigitMaxValue();
        return max;
    }
}
