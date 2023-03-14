package com.example.pooledjms.benchmark;

import com.example.pooledjms.AbstractBenchmarkRunner;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@State(Scope.Benchmark)
@SpringBootApplication
public class RawBenchmark extends AbstractBenchmarkRunner {
    @Benchmark
    public void run() {
        queueSender.send(RawBenchmark.class.getSimpleName().toLowerCase(), size);
    }
}
