FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-alpine
WORKDIR /producer

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

RUN ./mvnw dependency:resolve
CMD ["chmod +x ./mvnw", "spring-boot:run"]

