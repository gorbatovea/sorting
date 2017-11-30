package ru.mail.polis.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.mail.polis.sort.LSDSort;
import ru.mail.polis.sort.SortUtils;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class SimpleStringSortBench {
    //todo: классы extends AbstractSortOnComparisons и LSDSort
    LSDSort<SimpleString> lsdSort = new LSDSort<>();

    SimpleString[] current;

    @Setup(value = Level.Invocation)
    public void setUpInvocation(){
        current = SortUtils.generateSimpleString(1000);
    }

    @Benchmark
    public void measureSimpleStringSortBench(){
        lsdSort.sort(current);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SimpleStringSortBench.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
