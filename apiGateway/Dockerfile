FROM openjdk:17

EXPOSE 8058

ADD target/apiGateway-0.0.1-SNAPSHOT.jar apigateway.jar

ENTRYPOINT ["java", "-jar", "/apigateway.jar"]