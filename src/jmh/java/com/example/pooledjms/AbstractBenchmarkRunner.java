package com.example.pooledjms;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.TearDown;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.jms.core.JmsTemplate;

public abstract class AbstractBenchmarkRunner {

    public int size = 5_000;

    protected ConfigurableApplicationContext context;

    protected QueueSender queueSender;

    @Setup(Level.Iteration)
    public void setup() {
        final var sa = new SpringApplication(this.getClass());
        sa.setEnvironment(environment());
        context = sa.run();
        queueSender = new QueueSender(context.getBean(JmsTemplate.class));
    }

    @TearDown(Level.Iteration)
    public void teardown() {
        context.close();
    }

    protected ConfigurableEnvironment environment() {
        return new StandardEnvironment();
    }
}
