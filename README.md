# Pooled Bench

Benchmark pooled vs non-pooled jms.

Run the benchmark.

```shell
./gradlew clean jmh
```

### Results

Benchmark results "on my machine" with in-vm artemis.

```
Benchmark                 Mode  Cnt    Score    Error  Units
PooledBatchBenchmark.run  avgt   30  204.695 ▒ 10.698  ms/op
PooledBenchmark.run       avgt   30  254.126 ▒ 16.323  ms/op
RawBatchBenchmark.run     avgt   30   73.061 ▒  2.852  ms/op
RawBenchmark.run          avgt   30  134.534 ▒  5.904  ms/op
```

Strange result. Maybe we should connect to external artemis instance instead? Or try increase
the `AbstractBenchmarkRunner#size`.
