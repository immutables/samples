package org.immutables.samples.json;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class MicroBenchmarks {

  private static final int SIZE = 10;
  private Integer[] numbers;
  private ArrayList<Integer> numlist;
  private List<Integer> listnum;
  private ImmutableList<Integer> imnumlist;

  @Setup
  public void create() {
    numbers = new Integer[SIZE];
    for (int i = 0; i < numbers.length; i++) {
      numbers[i] = (int) (Math.random() * 10000);
    }
    numlist = new ArrayList<>();
    numlist.addAll(Arrays.asList(numbers));
    listnum = Arrays.asList(numbers);
    imnumlist = ImmutableList.copyOf(numbers);
  }

  @Benchmark
  public long arrayList() {
    long sum = 0;
    for (Integer integer : Collections.unmodifiableList(numlist)) {
      sum += integer;
    }
    return sum;
  }

  @Benchmark
  public long listArray() {
    long sum = 0;
    for (Integer integer : Collections.unmodifiableList(listnum)) {
      sum += integer;
    }
    return sum;
  }

  @Benchmark
  public long imlist() {
    long sum = 0;
    for (Integer integer : imnumlist) {
      sum += integer;
    }
    return sum;
  }
}
