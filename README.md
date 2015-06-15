samples
=======

Samples, benchmarks and experimentation with Immutables

JSON Benchmark
--------------
Created for Immutables 2.0 to assess the JSON infrastructure changes.

```
Benchmark                                             Mode  Samples     Score     Error  Units
o.i.s.j.JsonBenchmarks.autoJackson                    avgt        5   709.249 ±  19.170  us/op
o.i.s.j.JsonBenchmarks.immutablesGson                 avgt        5  1155.550 ±  48.843  us/op
o.i.s.j.JsonBenchmarks.immutablesGsonJackson          avgt        5   682.605 ±  20.839  us/op
o.i.s.j.JsonBenchmarks.pojoGson                       avgt        5  1402.759 ± 101.077  us/op
o.i.s.j.JsonBenchmarks.pojoGsonJackson                avgt        5   935.107 ±  58.210  us/op
o.i.s.j.JsonBenchmarks.pojoJackson                    avgt        5   721.767 ±  47.782  us/op
```

Read Immutables [JSON guide](http://immutables.github.io/json.html)
