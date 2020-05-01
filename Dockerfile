FROM openjdk:latest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 5555:7777
ENTRYPOINT ["java","-jar","/app.jar"]