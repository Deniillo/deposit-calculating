# Этап сборки
FROM eclipse-temurin:21 AS builder

# Зависимости для Gradle
RUN apt-get update && apt-get install -y curl findutils

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

COPY . .

# Копируем файлы Gradle
COPY gradlew .
COPY gradle ./gradle

# Даем права на выполнение Gradle wrapper
RUN chmod +x gradlew

# Сборка проекта
RUN ./gradlew :deposit-calculating-ui:shadowJar -x test -x spotlessCheck -x spotlessApply

# Этап запуска
FROM eclipse-temurin:21-ubi9-minimal

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем fat JAR из этапа сборки
COPY --from=builder /app/deposit-calculating-ui/build/libs/deposit-calculating-ui.jar deposit-calculating-ui.jar

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "deposit-calculating-ui.jar"]