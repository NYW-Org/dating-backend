FROM eclipse-temurin:21-jdk-aplpine

WORKDIR /app

COPY build/libs/*.jar app.jar

# Low Memory config
ENTRYPOINT ["java", "-XX:+UserSerialGC", "-Xms128m", "Xmx256", "-jar", "app.jar"]