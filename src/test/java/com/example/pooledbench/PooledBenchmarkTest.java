package com.example.pooledbench;

import com.example.testsupport.AbstractBenchmark;
import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@ActiveProfiles("pooled")
@DirtiesContext
public class PooledBenchmarkTest extends AbstractBenchmark {
    /**
     * The most important thing is to get Spring (autowired) components into the executing benchmark instance. To accomplish this you can do the
     * following
     * <p>
     * * set `forks` to 0 for the JMH runner to run the benchmarks within the same VM * store all @Autowired dependencies into static fields of the
     * surrounding class
     */
    private static JmsTemplate jmsTemplate;

    @Autowired
    void setJmsTemplate(JmsTemplate jmsTemplate) {
        PooledBenchmarkTest.jmsTemplate = jmsTemplate;
    }

    @Benchmark
    public void run() {
        for (int i = 0; i < size; i++) {
            jmsTemplate.send("test", session -> session.createTextMessage("hello"));
        }
    }
}
