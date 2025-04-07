FROM eclipse-temurin:17-jre-jammy
EXPOSE 8761
COPY target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server.jar
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]