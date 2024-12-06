# Sử dụng image chính thức của OpenJDK để chạy ứng dụng Java
FROM amazoncorretto:21.0.4

# Đặt thư mục làm việc trong container
WORKDIR /app

# Copy file JAR vào trong container (tên file JAR phải chính xác)
COPY target/chodoido_ute_service-0.0.1-SNAPSHOT.jar /app/app.jar

# Cổng mà ứng dụng Spring Boot sẽ lắng nghe
EXPOSE 8080

# Lệnh để chạy ứng dụng
ENTRYPOINT ["java", "-jar", "/app/app.jar"]












## Bước 1: Chọn image Maven để build ứng dụng
#FROM maven:3.9.8-amazoncorretto-21 AS build
#
## Bước 2: Đặt thư mục làm việc trong container
#WORKDIR /app
#
## Bước 3: Sao chép pom.xml vào container trước để tận dụng Docker cache
#COPY pom.xml .
#
## Bước 4: Tải dependencies Maven (không cần build toàn bộ ứng dụng ngay)
#RUN mvn install -DskipTests
#
## Bước 5: Sao chép mã nguồn vào container
#COPY src ./src
#
## Bước 6: Build ứng dụng và tạo file JAR
#RUN mvn clean package -DskipTests
#
## Bước 5: Tạo một image chính để chạy ứng dụng
#FROM amazoncorretto:21.0.4
#
#WORKDIR /app
## Bước 6: Copy file JAR từ image build vào image chính
#COPY --from=build /app/target/*.jar app.jar
#
#
## Bước 8: Chạy ứng dụng Spring Boot
#ENTRYPOINT ["java", "-jar", "/app.jar"]
