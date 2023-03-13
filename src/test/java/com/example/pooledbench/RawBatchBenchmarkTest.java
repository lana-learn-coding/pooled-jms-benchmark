package com.example.pooledbench;

import com.example.testsupport.AbstractBenchmark;
import jakarta.jms.JMSException;
import jakarta.jms.Session;
import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@DirtiesContext
public class RawBatchBenchmarkTest extends AbstractBenchmark {
    private static JmsTemplate jmsTemplate;

    @Autowired
    void setJmsTemplate(JmsTemplate jmsTemplate) {
        RawBatchBenchmarkTest.jmsTemplate = jmsTemplate;
    }

    @Benchmark
    public void run() throws JMSException {
        try (var connection = Objects.requireNonNull(jmsTemplate.getConnectionFactory()).createConnection();
             var session = connection.createSession(true, Session.SESSION_TRANSACTED)) {
            for (int i = 0; i < size; i++) {
                session.createProducer(session.createQueue("test"))
                        .send(session.createTextMessage("test"));
            }
            session.commit();
        }
    }
}
