package com.example.pooledjms.benchmark;

import com.example.pooledjms.AbstractBenchmarkRunner;
import org.openjdk.jmh.annotations.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@State(Scope.Benchmark)
@SpringBootApplication
public class RawBatchBenchmark extends AbstractBenchmarkRunner {
    @Benchmark
    public void run() {
        queueSender.sendBatched(size);
    }
}
