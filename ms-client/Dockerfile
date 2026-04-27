FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/ms-client-1.0.0-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","app.jar"]