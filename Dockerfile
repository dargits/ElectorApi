# Stage 1: Build ứng dụng
FROM maven:3-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Chạy ứng dụng
# Sử dụng base image Tomcat để chạy file .war
FROM tomcat:9-jdk17-openjdk-slim
WORKDIR /usr/local/tomcat/webapps

# Sao chép file .war đã được build vào thư mục webapps của Tomcat
# Tomcat sẽ tự động triển khai file .war này khi khởi động
COPY --from=build /app/target/ElcApp-0.0.1-SNAPSHOT.war ElcApp.war

# Mở cổng 8080 (cổng mặc định của Tomcat)
EXPOSE 8080

# Tomcat sẽ tự động chạy khi container khởi động
# Không cần lệnh ENTRYPOINT ["java", "-jar", ...]