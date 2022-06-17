FROM openjdk:18.0.1-jdk-oraclelinux7
EXPOSE 8080
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]