FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/task-manager-service-0.0.1-SNAPSHOT.jar task-manager-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","task-manager-service.jar"]