FROM openjdk:17-slim
EXPOSE 8080
COPY target/employee-management.jar target/app.jar
ENTRYPOINT ["java", "-jar", "target/app.jar"]