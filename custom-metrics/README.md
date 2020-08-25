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
java -javaagent:./agent/jmx_prometheus.jar=9103:./agent/jmx_prometheus.yaml  \
  -Djava.util.logging.manager=org.jboss.logmanager.LogManager  \
  -Xbootclasspath/p:./agent/jboss-logmanager.jar \
  -jar target/custom-metrics-thorntail.jar
```

Check that `http://localhost:9103/metrics` contains your metrics in prometheus format, you can configure this url to be scraped