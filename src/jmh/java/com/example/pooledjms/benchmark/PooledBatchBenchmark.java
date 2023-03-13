package com.example.pooledjms.benchmark;

import com.example.pooledjms.AbstractBenchmarkRunner;
import org.openjdk.jmh.annotations.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

@State(Scope.Benchmark)
@SpringBootApplication
public class PooledBatchBenchmark extends AbstractBenchmarkRunner {
    @Override
    protected ConfigurableEnvironment environment() {
        var environment = new StandardEnvironment();
        environment.setActiveProfiles("pooled");
        return environment;
    }

    @Benchmark
    public void run() {
        queueSender.sendBatched(size);
    }
}
