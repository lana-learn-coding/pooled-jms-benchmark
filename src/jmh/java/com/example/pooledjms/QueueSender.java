package com.example.pooledjms;

import jakarta.jms.JMSException;
import jakarta.jms.Session;
import org.springframework.jms.core.JmsTemplate;

import java.util.Objects;

public class QueueSender {
    private final JmsTemplate template;

    public QueueSender(JmsTemplate template) {
        this.template = template;
    }

    public void send(int size) {
        for (int i = 0; i < size; i++) {
            template.send("test", session -> session.createTextMessage("test"));
        }
    }

    public void sendBatched(int size) {
        try (var connection = Objects.requireNonNull(template.getConnectionFactory()).createConnection();
             var session = connection.createSession(true, Session.SESSION_TRANSACTED)) {
            for (int i = 0; i < size; i++) {
                session.createProducer(session.createQueue("test"))
                        .send(session.createTextMessage("test"));
            }
            session.commit();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
