FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-alpine
WORKDIR /consumer

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

RUN ./mvnw dependency:resolve
CMD ["./mvnw", "spring-boot:run"]

