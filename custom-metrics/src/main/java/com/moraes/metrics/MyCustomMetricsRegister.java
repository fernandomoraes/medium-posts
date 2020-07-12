package com.moraes.metrics;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.management.*;

@ApplicationScoped
public class MyCustomMetricsRegister {

    @Inject
    private MBeanServer mBeanServer;

    @Inject
    private MyCustomMetricsMBean myCustomMetricsMBean;

    private ObjectName name;

    void init(@Observes @Initialized(ApplicationScoped.class) Object event)
            throws NotCompliantMBeanException,
            InstanceAlreadyExistsException,
            MBeanRegistrationException,
            MalformedObjectNameException {

        final Class<? extends MyCustomMetricsMBean> objectClass = myCustomMetricsMBean.getClass();

        name = new ObjectName(
                String.format("%s:type=basic,name=%s", objectClass.getPackage().getName(), objectClass.getName())
        );

        mBeanServer.registerMBean(myCustomMetricsMBean, name);
    }

    @PreDestroy
    void preDestroy() {
        try {
            mBeanServer.unregisterMBean(name);
        } catch (InstanceNotFoundException | MBeanRegistrationException e) {
            throw new RuntimeException(e);
        }
    }


}
