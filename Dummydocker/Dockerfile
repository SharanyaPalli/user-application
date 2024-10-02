FROM openjdk:17-jdk-alpine
COPY target/commerzbank-1.0-SNAPSHOT.jar app.jar
WORKDIR /usr/appDockerfile
ENTRYPOINT ["java", "-jar", "myapp.jar"]
EXPOSE 8080