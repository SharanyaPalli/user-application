FROM openjdk:17-jdk-alpine
ADD target/UserData-1.0-SNAPSHOT.jar UserData-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/UserData-1.0-SNAPSHOT.jar"]
EXPOSE 8080