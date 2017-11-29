package ru.mail.polis.bench;

import org.openjdk.jmh.annotations.*;
import ru.mail.polis.sort.Sort;
import ru.mail.polis.sort.SortUtils;
import ru.mail.polis.structures.IntKeyObject;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class IntKeyObjectSortBench {
    //todo: классы extends AbstractSortOnComparisons и CountingSort
    IntKeyObject<Integer>[][] data;
    IntKeyObject<Integer>[] current;

    int index = 0;

    @Setup(value = Level.Trial)
    public void setUpTrial(){
        data = new IntKeyObject[10][100];
        current = new IntKeyObject[100];
        for(int i = 0; i < 10; i++){
            data[i] = SortUtils.generateArrayIntKeyObjects(100);
        }
    }

   @Setup(value = Level.Invocation)
   public void setUpInvocation(){
        current = data[index];
        index = (index + 1) % 10;
   }
}
