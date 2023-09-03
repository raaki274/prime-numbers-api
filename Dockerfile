FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY snapshot/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
