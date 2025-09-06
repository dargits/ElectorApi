# Sử dụng một base image của Java (ví dụ: OpenJDK 17)
# Đây là image nền tảng đã có sẵn Java Runtime Environment
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc bên trong container
WORKDIR /app

# Sao chép file .jar đã được build vào container
# 'target/elector-app-backend-0.0.1-SNAPSHOT.jar' là đường dẫn mặc định
# của file JAR khi bạn build dự án bằng Maven.
# Thay đổi tên file nếu cần thiết.
# 'java -jar app.jar' sẽ chạy file JAR đã được sao chép
COPY target/ElcApp-0.0.1-SNAPSHOT.jar app.jar
# Mở cổng 8080 để ứng dụng có thể truy cập từ bên ngoài container
EXPOSE 8080

# Lệnh để chạy ứng dụng khi container khởi động

ENTRYPOINT ["java", "-jar", "app.jar"]