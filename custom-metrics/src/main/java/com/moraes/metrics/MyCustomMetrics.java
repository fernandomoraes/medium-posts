package com.moraes.metrics;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class MyCustomMetrics implements MyCustomMetricsMBean {

    private final AtomicLong totalFoo = new AtomicLong();

    @Override
    public long getTotalFoo() {
        return totalFoo.get();
    }

    public void incrementFoo() {
        totalFoo.incrementAndGet();
    }
}
