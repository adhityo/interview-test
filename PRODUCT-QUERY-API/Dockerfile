FROM openjdk:17-jdk-slim-buster
EXPOSE 8080
ADD target/product-api-query-backend.jar product-api-query-backend.jar
ADD application-prod.properties application.properties

ENTRYPOINT ["java", "-jar", "/product-api-query-backend.jar","application.properties"]