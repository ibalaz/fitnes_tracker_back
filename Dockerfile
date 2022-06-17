FROM openjdk:18.0.1-jdk-oraclelinux7
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]