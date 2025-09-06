# Sử dụng base image của Java
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc bên trong container
WORKDIR /app

# Sao chép file .jar đã được build vào container
# Đảm bảo tên file này chính xác với file được tạo ra trong thư mục target/
COPY target/ElcApp-0.0.1-SNAPSHOT.jar app.jar

# Mở cổng 8080 của ứng dụng
EXPOSE 8080

# Lệnh để chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]