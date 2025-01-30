# Используем официальный образ OpenJDK 17
FROM openjdk:17-jdk-alpine

# Устанавливаем рабочую директорию внутри контейнера
VOLUME /tmp

# Копируем jar-файл в контейнер
ARG JAR_FILE=target/monitor-sensors-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "/app.jar"]