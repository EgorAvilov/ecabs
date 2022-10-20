##FROM maven:3.8.5-jdk-11 AS build
#COPY src /app/src
#COPY pom.xml /app
#RUN mvn -f app/pom.xml clean package -DskipTests
#
#
#FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-alpine
#COPY --from=build app/target/producer.jar /app-service/producer.jar
#WORKDIR /app-service
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","producer.jar"]

FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-alpine
EXPOSE 8080
WORKDIR /producer

COPY .mvn/ .mvn
COPY mvnw ./producer/pom.xml ./
COPY ./producer/src ./src

RUN ./mvnw dependency:resolve
CMD ["./mvnw", "spring-boot:run"]

