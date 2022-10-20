FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-alpine
EXPOSE 8081
WORKDIR /consumer

COPY .mvn/ .mvn
COPY mvnw ./consumer/pom.xml ./
COPY ./consumer/src ./src

RUN ./mvnw dependency:resolve
CMD ["./mvnw", "spring-boot:run"]

