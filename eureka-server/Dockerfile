FROM openjdk:21
EXPOSE 8761
ADD target/eureka-server-0.0.1-SNAPSHOT.jar eureka.jar
ENTRYPOINT ["java", "-jar", "eureka.jar"]
