# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="alfredternor@gmail.com"



ARG JAR_FILE=./build/libs/scholarship.jar

# Add the application's jar to the container
COPY ${JAR_FILE} /scholarship.jar

# Make port 8083 available to the world outside this container
EXPOSE 8080
EXPOSE 3307

VOLUME /tmp


# Run the jar file
ENTRYPOINT ["ls"]

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/scholarship.jar"]


