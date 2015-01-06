package org.immutables.samples.json;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class NarrowJsonBenchmarks {

  private JsonBenchmarks jsonBenchmarks;

  @Setup
  public void setup() throws IOException {
    jsonBenchmarks = new JsonBenchmarks();
    jsonBenchmarks.setup();
  }

  @Benchmark
  public String immutablesGsonJackson() throws IOException {
    return jsonBenchmarks.immutablesGsonJackson();
  }
}
