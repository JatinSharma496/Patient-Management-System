# base image   build the code
FROM maven:3.9.9-eclipse-temurin-21 AS builder
# working dir inside the container
WORKDIR /app

# add all the dependency  copy the pom.xml to the /app
COPY pom.xml .

# install the dipendency , cache the dependency if start the c - b in attach mode
RUN  mvn dependency:go-offline -B

# copy all the code
COPY src ./src
#  create  single jar file
RUN mvn clean package

# Run buid code in jdk
FROM openjdk:21-jdk  AS runner
# same
WORKDIR /app
# copy the patient service snapshot  at builder step to the app at run
COPY --from=builder ./app/target/billing-service-0.0.1-SNAPSHOT.jar ./app.jar

# app runs on 4000
EXPOSE 4001
#for grpc server
EXPOSE 9001
# to start our code
ENTRYPOINT ["java" , "-jar","app.jar"]

