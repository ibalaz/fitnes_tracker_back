FROM openjdk:8-jdk-alpine
EXPOSE 8080
ENV PORT 8080
ENV HOST 0.0.0.0
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]