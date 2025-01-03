FROM openjdk:17-slim
EXPOSE 9000
COPY target/employee-management.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]