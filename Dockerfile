FROM jdk11-alpine AS build
COPY --chown=gradle:gradle . build/libs/
WORKDIR .
RUN gradle bootJar	 

# For Java 11, try this
FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=build/libs/recipe-crawler-*.jar
ENV env1="asdf"
# cd /opt/app
WORKDIR .

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} recipe-crawler.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","recipe-crawler.jar"]