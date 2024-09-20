# Step 1: Use a base image for JDK 17 (hoặc JDK phiên bản bạn đang sử dụng)
FROM openjdk:17-jdk-alpine

# Step 2: Sao chép tệp JAR của ứng dụng vào thư mục làm việc
COPY target/*.jar $APP_HOME/myapp.jar

# Step 3: Chạy ứng dụng Spring Boot khi container khởi chạy
ENTRYPOINT ["java", "-jar", "myapp.jar"]
