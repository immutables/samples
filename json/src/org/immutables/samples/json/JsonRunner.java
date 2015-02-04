package org.immutables.samples.json;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JsonRunner {

  public static void main(String... args) throws RunnerException {
    new Runner(new OptionsBuilder()
        .include(".*" + JsonBenchmarks.class.getSimpleName() + ".*")
        .warmupIterations(7)
        .measurementIterations(5)
        .forks(1)
        .build()).run();
  }

  public static void main2(String... args) throws RunnerException {
    new Runner(new OptionsBuilder()
        .include(".*" + NarrowJsonBenchmarks.class.getSimpleName() + ".*")
        .warmupIterations(30)
        .measurementIterations(30)
        .forks(1)
        .build()).run();
  }

}
