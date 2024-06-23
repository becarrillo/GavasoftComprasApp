FROM openjdk:17-jdk-slim
ENV MYSQL_DBNAME=railway
ENV MYSQL_HOST=monorail.proxy.rlwy.net
ENV MYSQL_PASSWORD=wfstQjNkaYDDdqHfeUqLgphOdCnvGIbR
ENV MYSQL_PORT=29147
ENV MYSQL_USER=root
ARG COMPRAS_APP_JAR_FILE=compras-app/target/compras-app-0.0.1-SNAPSHOT.jar
COPY ${COMPRAS_APP_JAR_FILE} compras-app.jar

EXPOSE 8084
ENTRYPOINT ["java", "-jar", "compras-app.jar"]