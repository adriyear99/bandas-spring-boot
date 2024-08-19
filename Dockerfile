FROM openjdk:17
LABEL maintainer="adriano"
ADD target/spring-boot-adriano-0.0.1-SNAPSHOT.jar spring-boot-adriano.jar
ENTRYPOINT ["java","-jar","spring-boot-adriano.jar"]
