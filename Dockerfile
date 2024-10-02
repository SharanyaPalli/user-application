FROM openjdk:17-jdk-alpine
ADD target/commerzbank-1.0-SNAPSHOT.jar commerzbank-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/commerzbank-1.0-SNAPSHOT.jar"]
EXPOSE 8080