FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} even-bus.jar
ENTRYPOINT ["java","-jar","/even-bus.jar"]
EXPOSE 4002