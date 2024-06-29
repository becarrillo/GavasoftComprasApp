FROM openjdk:17-jdk-slim
ARG COMPRAS_APP_JAR_FILE=target/compras-app-0.0.1-SNAPSHOT.jar
COPY ${COMPRAS_APP_JAR_FILE} compras-app.jar

ENTRYPOINT ["java", "-jar", "compras-app.jar"]