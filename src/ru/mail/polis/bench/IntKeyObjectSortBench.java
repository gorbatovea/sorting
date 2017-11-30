package ru.mail.polis.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.mail.polis.sort.CountingSort;
import ru.mail.polis.sort.Sort;
import ru.mail.polis.sort.SortUtils;
import ru.mail.polis.structures.IntKeyObject;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class IntKeyObjectSortBench {
    //todo: классы extends AbstractSortOnComparisons и CountingSort

    CountingSort<IntKeyObject> countingSort = new CountingSort<>();

    IntKeyObject<Integer>[] current;

    @Setup(value = Level.Invocation)
    public void setUpInvocation(){
        current = SortUtils.generateArrayIntKeyObjects(1000);
    }

    @Benchmark
    public void measureIntKeyObjectSortBench(){
        countingSort.sort(current);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(IntKeyObjectSortBench.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
