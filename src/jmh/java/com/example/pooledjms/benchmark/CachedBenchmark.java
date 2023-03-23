package com.example.pooledjms.benchmark;

import com.example.pooledjms.AbstractBenchmarkRunner;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

@State(Scope.Benchmark)
@SpringBootApplication
public class CachedBenchmark extends AbstractBenchmarkRunner {
    @Override
    protected ConfigurableEnvironment environment() {
        var environment = new StandardEnvironment();
        environment.setActiveProfiles("cached");
        return environment;
    }

    @Benchmark
    public void run() {
        queueSender.send(CachedBenchmark.class.getSimpleName().toLowerCase(), size);
    }
}
