# Pooled Bench

Benchmark pooled vs non-pooled jms.

Run the benchmark.

```shell
./gradlew clean jmh
```

### Results

Benchmark results "on my machine" with in-vm artemis.

```
Benchmark                 Mode  Cnt    Score   Error  Units
PooledBatchBenchmark.run  avgt   30  781.619 ± 6.521  ms/op
PooledBenchmark.run       avgt   30  978.716 ± 6.870  ms/op
RawBatchBenchmark.run     avgt   30  285.838 ± 4.618  ms/op
RawBenchmark.run          avgt   30  487.554 ± 3.098  ms/op
```

Strange result. I was expected the `RawBenchmark.run` to be slower than `Pooled*` ones. Maybe we should connect to
external artemis instance instead? Or try increase the `AbstractBenchmarkRunner#size`.
