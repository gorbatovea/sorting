package ru.mail.polis.sort.valid;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ru.mail.polis.sort.CountingSort;
import ru.mail.polis.sort.LSDSort;
import ru.mail.polis.sort.SimpleSortOnComparisons;
import ru.mail.polis.structures.IntKeyObject;
import ru.mail.polis.structures.IntKeyStringValueObject;
import ru.mail.polis.structures.Numerical;
import ru.mail.polis.structures.SimpleInteger;

/**
 * Created by Nechaev Mikhail
 * Since 14/11/2017.
 */
public class SimpleTest {

    @Test
    public void test01() throws IOException {
        SimpleSortOnComparisons<String> simpleSort = new SimpleSortOnComparisons<>();
        String[] array = new String[]{"abc", "bcd", "cde", "acd", "zxy", "bba"};
        simpleSort.sort(array);
        Assert.assertArrayEquals(new String[]{"abc", "acd", "bba", "bcd", "cde", "zxy"}, array);
    }

    @Test
    public void testLSD() throws IOException {
        LSDSort<SimpleInteger> lsdSort = new LSDSort<>();
        Numerical[] array = new Numerical[]{new SimpleInteger(10), new SimpleInteger(20), new SimpleInteger(30)};
        lsdSort.sort((SimpleInteger[])array);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCountingSort() throws IOException {
        CountingSort<IntKeyObject<String>> countingSort = new CountingSort<>();
        List<IntKeyObject<String>> list = Arrays.asList(new IntKeyStringValueObject(1, "abc"), new IntKeyStringValueObject(2, "bcd"));
        IntKeyObject<String>[] array = (IntKeyObject<String>[]) list.toArray();
        countingSort.sort(array);
    }
}
