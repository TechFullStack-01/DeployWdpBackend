FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/wdpvendas-0.0.1.jar
COPY ${JAR_FILE} app_wdpvendas.jar
EXPOSE 8443
ENTRYPOINT ["java", "-jar", "app_wdpvendas.jar"]