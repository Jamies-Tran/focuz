FROM gradle:jdk-21-and-22-alpine AS build
WORKDIR /business-service/auth
COPY build.gradle .
COPY settings.gradle .
COPY gradle ./gradle
COPY .env .
COPY gradle.properties .
RUN gradle dependencies --no-daemon
COPY src ./src
RUN gradle clean build -x test --no-daemon

FROM eclipse-temurin:21-jre-alpine
COPY --from=build ./business-service/auth/build/libs/*.jar auth.jar
ENTRYPOINT ["java", "-jar", "auth.jar"]