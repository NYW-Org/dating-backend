# Build
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew bootJar -x test

# Create image
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/app.jar app.jar

# Low Memory config
ENTRYPOINT ["java", "-XX:+UseSerialGC", "-Xms128m", "-Xmx256m", "-jar", "app.jar"]
