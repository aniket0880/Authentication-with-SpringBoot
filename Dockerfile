FROM openjdk:25-jdk-bullseye
ADD target/auth.jar auth.jar
ENTRYPOINT ["java","-jar","/auth.jar"]