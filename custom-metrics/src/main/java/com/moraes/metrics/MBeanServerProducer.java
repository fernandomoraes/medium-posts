package com.moraes.metrics;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;

@ApplicationScoped
public class MBeanServerProducer {

    @Produces
    @ApplicationScoped
    public MBeanServer mBeanServer() {
        return ManagementFactory.getPlatformMBeanServer();
    }

}
