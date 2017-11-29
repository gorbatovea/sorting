package ru.mail.polis.sort;
import ru.mail.polis.structures.DoubleLinkedList;
import ru.mail.polis.structures.Node;

import java.util.Comparator;
import java.util.Iterator;

public class InsertionSort<T> extends AbstractSortOnComparisons<T> implements Sort<T>{

    public InsertionSort(){
        super();
    }

    public InsertionSort(Comparator<? super T> comparator){
        super();
        setComparator(comparator);
    }

    public void sort(T[] arr) {
        if (arr.length == 0) return;
        DoubleLinkedList<T> list = new DoubleLinkedList<>();
        Node<T> currentNode;
        for (T value : arr) {
            if (list.size() == 0) {
                list.addFront(value);
                if (arr.length == 1) return;
                continue;
            }
            currentNode = list.getHead();
            int index = 0;
            while (currentNode.next != null) {
                if (!greater(currentNode.next.value, value)) currentNode = currentNode.next;
                else break;
                index++;
            }
            if (lesser(currentNode.value, value))
                index++;
            list.insert(index, value);
        }
        Iterator<T> iterator = list.iterator();
        int index = 0;
        while(iterator.hasNext()) arr[index++] = iterator.next();

    }
}
