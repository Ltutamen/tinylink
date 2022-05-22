FROM openjdk:17-slim

COPY build/libs/lab1-site*.jar /home/app.jar
CMD ["java", "-jar", "/home/app.jar"]

