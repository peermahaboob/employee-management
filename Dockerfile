FROM openjdk:17-slim
EXPOSE 9001
COPY target/employee-management.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]