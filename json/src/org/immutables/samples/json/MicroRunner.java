package org.immutables.samples.json;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class MicroRunner {

  public static void main(String... args) throws RunnerException {
    new Runner(new OptionsBuilder()
        .include(".*" + HashCodeBench.class.getSimpleName() + ".*")
        // .include(".*" + MicroBenchmarks.class.getSimpleName() + ".*")
        .warmupIterations(7)
        .measurementIterations(5)
        .forks(1)
        .build()).run();
  }
}
