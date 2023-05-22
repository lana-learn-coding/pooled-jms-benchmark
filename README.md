# Pooled-JMS Benchmark

Benchmark spring pooled vs non-pooled JmsTemplate.

Run the benchmark.

```shell
./gradlew clean jmh
```

## Results

### Strange Result?

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

### Cached Pool

Let's run the benchmark with new `CachedBenchmark` (on a different machine)

```
Benchmark                 Mode  Cnt     Score    Error  Units
CachedBatchBenchmark.run  avgt   30    29.024 ±  0.111  ms/op
CachedBenchmark.run       avgt   30   706.141 ± 42.811  ms/op
PooledBatchBenchmark.run  avgt   30    29.168 ±  0.109  ms/op
PooledBenchmark.run       avgt   30  1589.257 ± 72.535  ms/op
RawBatchBenchmark.run     avgt   30    28.943 ±  0.128  ms/op
RawBenchmark.run          avgt   30   710.760 ± 44.869  ms/op
```

Notice that our new `CachedBenchmark` give similar result to `RawBenchmark`? It turned out that **spring default use
cached connection factory with the size of 1**. This benchmark does not account concurrency, so the 1-cached session is
very effective, this also explain the _Strange Result?_ happened in the earlier benchmark

### Final Result

Let turn of the default cached jms and run again, on the same machine:

```
Benchmark                 Mode  Cnt     Score     Error  Units
CachedBatchBenchmark.run  avgt   30    29.073 ±   0.135  ms/op
CachedBenchmark.run       avgt   30   691.365 ±  35.387  ms/op
PooledBatchBenchmark.run  avgt   30    29.187 ±   0.106  ms/op
PooledBenchmark.run       avgt   30  1474.955 ±  86.242  ms/op
RawBatchBenchmark.run     avgt   30    29.199 ±   0.110  ms/op
RawBenchmark.run          avgt   12  6660.094 ± 934.057  ms/op
```

Now we can see that `RawBenchmark` is about 5x times slower than `PooledBenchmark` and about 10x times slower
than `CachedBenchmark`
