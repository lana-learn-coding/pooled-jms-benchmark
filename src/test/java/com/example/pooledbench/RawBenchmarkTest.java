package com.example.pooledbench;

import com.example.testsupport.AbstractBenchmark;
import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@DirtiesContext
public class RawBenchmarkTest extends AbstractBenchmark {
    private static JmsTemplate jmsTemplate;

    @Autowired
    void setJmsTemplate(JmsTemplate jmsTemplate) {
        RawBenchmarkTest.jmsTemplate = jmsTemplate;
    }

    @Benchmark
    public void run() {
        for (int i = 0; i < size; i++) {
            jmsTemplate.send("test", session -> session.createTextMessage("hello"));
        }
    }
}
