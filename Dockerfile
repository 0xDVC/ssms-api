FROM amazoncorretto:17-alpine-jdk
ENV SPRING_PROFILES_ACTIVE=docker

WORKDIR /app

COPY . /app

RUN apk update && apk add --no-cache curl

EXPOSE 8080

# CMD ["java", "-jar", "target/my-spring-boot-app.jar"]