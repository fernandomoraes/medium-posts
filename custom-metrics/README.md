# custom-metrics

App demo utilizing:
- Java
- Thorntail
- JMX
- JMX Prometheus exporter

## build

```.shell script
mvn clean package
```


## run

 ```.shell script
java -javaagent:./agent/jmx_prometheus.jar=9103:./agent/jmx_prometheus.yaml -Djava.util.logging.manager=org.jboss.logmanager.LogManager -Xbootclasspath/p:./agent/jboss-logmanager.jar -Djboss.modules.system.pkgs=org.jboss.byteman,com.manageengine,org.jboss.logmanager -jar "target/custom-metrics-thorntail.jar"


java -javaagent:./agent/jmx_prometheus.jar=9103:./agent/jmx_prometheus.yaml \
    -Djboss.modules.system.pkgs=org.jboss.byteman,com.manageengine,org.jboss.logmanager \
    -jar target/custom-metrics-thorntail.jar 

java \
    -javaagent:agent/jmx_prometheus.jar=9103:agent/jmx_prometheus.yml \
    -Djava.util.logging.manager=org.jboss.logmanager.LogManager -Xbootclasspath/p:agent/jboss-logmanager.jar \
    -Djboss.modules.system.pkgs=org.jboss.byteman,com.manageengine,org.jboss.logmanager.LogManager \
    -jar target/custom-metrics-thorntail.jar 
```