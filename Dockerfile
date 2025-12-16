FROM gradle:8-jdk17 AS build
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . .
RUN gradle bootJar --no-daemon

FROM eclipse-temurin:17-jre-jammy
ARG JAR_FILE=build/libs/*.jar
COPY --from=build /home/gradle/project/${JAR_FILE} /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Xms256m","-Xmx512m","-jar","/app/app.jar"]