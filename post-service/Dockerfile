FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} post-service.jar
ENTRYPOINT ["java","-jar","/post-service.jar"]
EXPOSE 4000