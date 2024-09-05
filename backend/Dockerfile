FROM maven:3.9.9-eclipse-temurin-22-jammy AS builder
WORKDIR /builder

COPY . .

RUN mvn package

###########################################################

FROM openjdk:22-bullseye AS image
WORKDIR /app

COPY --from=builder builder/target/jobwait-0.0.1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
