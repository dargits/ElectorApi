# Giai đoạn build
FROM maven:3-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Giai đoạn chạy
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/ElcApp-0.0.1-SNAPSHOT.jar ElcApp.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","ElcApp.jar"]
