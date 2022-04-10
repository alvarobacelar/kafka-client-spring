## Application Kafka Producer and Consumer
This application is a simple example how to producer and consumer in Kafka Cluster

For start up this app, you can run docker build whit bellow command:
```
docker build -t <image-name> .
```

Or, if you want running in Kubernet cluster, you can use the file ```app-k8s.yaml```
```
kubectl apply -f app-k8s.yaml
```
This file will be pulling image from my docker hub ```alvarobacelar/kafka-client``` 

### Monitoring
You can monitoring this app using Prometheus and Grafana. When you up this app will be expose a monitoring endpoint.
```
<ip.app>:8081/actuator/prometheus
```
If you have a prometheus and grafana, you can import [this dashboard](https://grafana.com/grafana/dashboards/16088) and see a broad view of your consumer metrics.
