package org.immutables.samples.json;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JsonRunner {

  public static void main(String... args) throws RunnerException {
    new Runner(new OptionsBuilder()
        .include(".*" + JsonBenchmarks.class.getSimpleName() + ".*")
        .warmupIterations(7)
        .measurementIterations(7)
        .forks(1)
        .build()).run();
  }
}
