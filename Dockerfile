FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=target/m-inder-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} m-inder-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "m-inder-0.0.1-SNAPSHOT.jar"]