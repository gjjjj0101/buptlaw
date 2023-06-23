FROM --platform=$BUILDPLATFORM maven:3.8.4-jdk-8-slim AS build
WORKDIR /workspace/backend
COPY  ./ /workspace/backend/

COPY ./pom.xml /workspace/backend/pom.xml
RUN mvn dependency:go-offline -B

RUN mvn clean -DskipTests package

FROM openjdk:8-jre-alpine
WORKDIR /workspace/backend

COPY ./start-up.sh /workspace/backend/start-up.sh
RUN chmod +x ./start-up.sh
COPY --from=build /workspace/backend/*/target/*.jar /workspace/backend/
RUN ls -al

CMD ["/workspace/backend/start-up.sh"]
