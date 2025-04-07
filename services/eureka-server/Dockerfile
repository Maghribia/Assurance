FROM openjdk:17
EXPOSE 8024
ADD target/eureka-server-0.0.1-SNAPSHOT.jar eureka.jar
ENTRYPOINT ["java", "-jar", "eureka.jar"]