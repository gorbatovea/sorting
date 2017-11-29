package ru.mail.polis.bench;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import ru.mail.polis.sort.*;
import ru.mail.polis.structures.IntKeyObject;
import ru.mail.polis.structures.Numerical;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;

/**
 * Created by Nechaev Mikhail
 * Since 20/11/16.
 */

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class AverageTimeBench {

    CountingSort<IntKeyObject> countingSort = new CountingSort<>();
    QuickSort<Integer> quickSort = new QuickSort<>();
    LSDSort<SimpleString> lsdSort = new LSDSort<>();

    int[][] intData;
    int[] intCurrent;

    IntKeyObject[][] intKeyData;
    IntKeyObject[] intKeyCurrent;

    Integer[][] integerData;
    Integer[] integerCurrent;

    SimpleString[][] simpleData;
    SimpleString[] simpleDataCurrent;

    int index;

    @Setup(value = Level.Trial)
    public void setUpTrial() {
        intData = new int[10][100];
        integerData = new Integer[10][100];
        intKeyData = new IntKeyObject[10][100];
        simpleData = new SimpleString[10][100];

        for (int i = 0; i < 10; i++) {
            //define arrays here
            intData[i] = SortUtils.generateArray(100);
            integerData[i] = SortUtils.generateIntgerArray(100);
            intKeyData[i] = SortUtils.generateArrayIntKeyObjects(100);
            simpleData[i] = SortUtils.generateSimpleString(100);
        }
    }

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        intCurrent = Arrays.copyOf(intData[index], intData[index].length);
        intKeyCurrent = Arrays.copyOf(intKeyData[index], intKeyData[index].length);
        integerCurrent = Arrays.copyOf(integerData[index], integerData[index].length);
        simpleDataCurrent = Arrays.copyOf(simpleData[index], simpleData[index].length);
        index = (index + 1) % 10;
    }

    @Benchmark
    public void measureBubbleSort() {
        BubbleSort.sort(intCurrent);
    }

    @Benchmark
    public void measureCountingSort() {
        countingSort.sort(intKeyCurrent);
    }

    @Benchmark
    public void measureQuickSort() {
        quickSort.sort(integerCurrent);
    }

    @Benchmark
    public void measureLSDSort() {
        lsdSort.sort(simpleDataCurrent);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(AverageTimeBench.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
