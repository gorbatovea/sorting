package ru.mail.polis.sort;

import ru.mail.polis.structures.IntKeyObject;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;
import ru.mail.polis.structures.SomeIntKeyObject;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SortUtils {

    private static final Random r = ThreadLocalRandom.current();
    private static final int FIRST_CHAR = 97;
    private static final int LAST_CHAR = 122;

    public static void swap(int[] a, int i, int j) {
        int x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    public static <T>void swap(T[] list, int i, int j){
        T temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    public static int[] generateArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        for (int i = a.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            SortUtils.swap(a, i, j);
        }
        return a;
    }

    public static Integer[] generateIntgerArray(int n) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        for (int i = a.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            SortUtils.swap(a, i, j);
        }
        return a;
    }

    public static boolean isArraySorted(int[] a) {
        boolean isSorted = true;
        for (int i = 0; i < a.length - 1 && isSorted; i++) {
            isSorted = a[i] <= a[i + 1];
        }
        return isSorted;
    }

    public static <T extends Comparable<? super T>> boolean isArraySorted(T[] array) {
        boolean isSorted = true;
        for (int i = 0; i < array.length - 1 && isSorted; i++) {
            isSorted = array[i].compareTo(array[i + 1]) <= 0;
        }
        return isSorted;
    }

    public static boolean isArraySorted(SimpleString[] array) {
        boolean isSorted = true;
        for (int i = 0; i < array.length - 1 && isSorted; i++) {
            isSorted = array[i].compareTo(array[i + 1]) <= 0;
        }
        return isSorted;
    }

    public static <T> boolean isArraySorted(T[] array, Comparator<T> comparator) {
        boolean isSorted = true;
        for (int i = 0; i < array.length - 1 && isSorted; i++) {
            isSorted = comparator.compare(array[i], array[i + 1]) <= 0;
        }
        return isSorted;
    }

    public static IntKeyObject[] generateArrayIntKeyObjects(int size){
        IntKeyObject[] list = new IntKeyObject[size];
        for(int i = 0; i < list.length; i++){
            list[i] = new SomeIntKeyObject(i + 1, i + 1);
        }
        for (int i = list.length - 1; i >= 0; i--) {
            int j = r.nextInt(i + 1);
            SortUtils.swap(list, i, j);
        }
        return list;
    }

    public static SimpleString[] generateSimpleString(int size){
        SimpleString[] list = new SimpleString[size];
        int length = 1 + (int)(Math.random() * size);
        for(int j = 0; j < size; j++){
            String tempStr = "";
            for(int i = 1; i <= length; i++){
                int chr = FIRST_CHAR + (int)(Math.random() * (LAST_CHAR - FIRST_CHAR));
                tempStr = tempStr.concat("" + (char)chr);
            }
            list[j] = new SimpleString(tempStr);
        }
        return list;
    }

    public static SimpleInteger[] generateSimpleInteger(int size){
        SimpleInteger[] list = new SimpleInteger[size];
        for(int i = 0; i < size; i++){
            list[i] = new SimpleInteger(i + 1);
        }
        for (int i = list.length - 1; i >= 0; i--) {
            int j = r.nextInt(i + 1);
            SortUtils.swap(list, i, j);
        }
        return list;
    }

    public static void printArray(SimpleString[] list){
        for(SimpleString object : list){
            System.out.println(object.toString());
        }
    }
}