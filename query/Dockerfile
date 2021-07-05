FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} query-service.jar
ENTRYPOINT ["java","-jar","/query-service.jar"]
EXPOSE 4003