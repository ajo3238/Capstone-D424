FROM ubuntu:latest
LABEL authors="annaj"

ENTRYPOINT ["top", "-b"]

FROM openjdk:17-jdk-alpine
COPY target/docker-java-jar-0.0.2-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]