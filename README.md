# Pooled-JMS Benchmark

Benchmark spring pooled vs non-pooled JmsTemplate.

Run the benchmark.

```shell
./gradlew clean jmh
```

## Results

Benchmark results "on my machine" with in-vm artemis.

```
Benchmark                 Mode  Cnt    Score    Error  Units
PooledBatchBenchmark.run  avgt   30   43.960 ±  2.649  ms/op
PooledBenchmark.run       avgt   30  954.912 ±  8.487  ms/op
RawBatchBenchmark.run     avgt   30   42.822 ±  1.782  ms/op
RawBenchmark.run          avgt   30  499.594 ± 19.711  ms/op
```

Strange result. I was expected the `RawBenchmark.run` to be slower than `Pooled*` ones. Maybe we should try increase
the `AbstractBenchmarkRunner#size` ~~or connect to external artemis instance instead?~~. Connect to external artemis
instance on different machine give the same result.
