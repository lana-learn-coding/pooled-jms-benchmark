package com.example.pooledbench;

import org.openjdk.jmh.annotations.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

@State(Scope.Benchmark)
@SpringBootApplication
public class PooledBenchmark extends RawBenchmark {
    @Override
    protected ConfigurableEnvironment environment() {
        var environment = new StandardEnvironment();
        environment.setActiveProfiles("pooled");
        return environment;
    }
}
