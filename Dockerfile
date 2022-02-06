#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip=true

#
# Package stage
#
FROM openjdk:11-jre-slim

ENV KAFKA_SERVER 157.230.203.216:9094

WORKDIR /usr/app
COPY --from=build /home/app/target/kafka-lab-0.0.1-SNAPSHOT.jar /usr/app/consumer-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java", "-Dcom.sun.jndi.ldap.object.disableEndpointIdentification=true", "-jar", "/usr/app/consumer-0.0.1-SNAPSHOT.jar"]