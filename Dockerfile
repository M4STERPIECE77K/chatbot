FROM gradle:8.8-jdk21-alpine AS build
WORKDIR /home/gradle/src

COPY build.gradle settings.gradle gradlew ./
COPY gradle gradle
RUN chmod +x gradlew
RUN ./gradlew dependencies > /dev/null 2>&1 || true

COPY src src
RUN ./gradlew clean bootJar -x test

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]