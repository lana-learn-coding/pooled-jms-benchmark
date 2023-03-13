package com.example.pooledbench;

import jakarta.jms.JMSException;
import jakarta.jms.Session;
import org.openjdk.jmh.annotations.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@State(Scope.Benchmark)
@SpringBootApplication
public class RawBatchBenchmark extends AbstractBenchmarkRunner {
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
