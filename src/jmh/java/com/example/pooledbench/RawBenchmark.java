package com.example.pooledbench;

import org.openjdk.jmh.annotations.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@State(Scope.Benchmark)
@SpringBootApplication
public class RawBenchmark extends AbstractBenchmarkRunner {
    @Benchmark
    public void run() {
        for (int i = 0; i < size; i++) {
            jmsTemplate.send("test", session -> session.createTextMessage("hello"));
        }
    }
}
