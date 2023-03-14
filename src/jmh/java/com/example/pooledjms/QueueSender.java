package com.example.pooledjms;

import jakarta.jms.JMSException;
import jakarta.jms.Session;
import org.springframework.jms.core.JmsTemplate;

import java.util.Objects;

public class QueueSender {
    private static final String message = """
            Lorem Ipsum is simply dummy text of the printing and typesetting industry.
            Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
            when an unknown printer took a galley of type and scrambled it to make a type specimen book.
            """;

    private final JmsTemplate template;

    public QueueSender(JmsTemplate template) {
        this.template = template;
    }

    public void send(String destination, int size) {
        for (int i = 0; i < size; i++) {
            template.send(destination, session -> session.createTextMessage(message));
        }
    }

    public void sendBatched(String destination, int size) {
        try (var connection = Objects.requireNonNull(template.getConnectionFactory()).createConnection();
             var session = connection.createSession(true, Session.SESSION_TRANSACTED);
             var producer = session.createProducer(session.createQueue(destination))) {
            for (int i = 0; i < size; i++) {
                producer.send(session.createTextMessage(message));
            }
            session.commit();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
