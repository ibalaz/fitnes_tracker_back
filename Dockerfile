FROM openjdk:11.0.10-jdk-slim-buster
EXPOSE 8080
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]