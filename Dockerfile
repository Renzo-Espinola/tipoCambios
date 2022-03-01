FROM openjdk:8-jdk-alpine
RUN addgroup -S tipocambios && adduser -S admin -G tipocambios
USER admin:tipocambios
VOLUME /TMP
ARG JAR_FILE=target/*.jar
ADD target/${JAR_FILE} app.jar
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]
