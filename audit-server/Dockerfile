FROM registry.access.redhat.com/ubi9/openjdk-17-runtime:1.16-1.1693315923

# Refer to Maven build -> finalName
ARG JAR_FILE=target/audit-server-1.0.0.jar

# cd /opt/app
WORKDIR /app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

#ENV SPRING_PROFILE=postgres

# java -jar /opt/app/app.jar
#ENTRYPOINT ["java","-Dspring-boot.run.profiles=postgres","-jar","app.jar"]

#run with environment variable
ENTRYPOINT java -Dspring.profiles.active=$SPRING_PROFILE -jar app.jar
