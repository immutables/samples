package org.immutables.samples.json;

import org.openjdk.jmh.annotations.Benchmark;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

class HashCompute {
  private final double db;
  private final int in;

  public HashCompute(int in, double db) {
    this.in = in;
    this.db = db;
  }

  @Override
  public int hashCode() {
    int h = 31;
    h = h * 17 + in;
    h = h * 17 + Double.hashCode(db);
    return h;
  }
}

class HashCompute2 {
  private final double db;
  private final int in;

  public HashCompute2(int in, double db) {
    this.in = in;
    this.db = db;
  }

  @Override
  public int hashCode() {
    int h = 31;
    h = h * 17 + in;
    h = h * 17 + new Double(db).hashCode();
    return h;
  }
}

@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class HashCodeBench {

  private static final HashCompute HASH_COMPUTE = new HashCompute(122, Math.E);
  private static final HashCompute2 HASH_COMPUTE2 = new HashCompute2(122, Math.E);

  @Benchmark
  public int c1() {
    return HASH_COMPUTE.hashCode() + HASH_COMPUTE.hashCode() + HASH_COMPUTE.hashCode();
  }

  @Benchmark
  public int c2() {
    return HASH_COMPUTE2.hashCode() + HASH_COMPUTE2.hashCode() + HASH_COMPUTE2.hashCode();
  }
}
