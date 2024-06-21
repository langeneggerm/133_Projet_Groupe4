FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
#CONF SERVER
#ENV DATABASE_URL=jdbc:mysql://mysql-nerf:3306/bd_magasin
#ENV DATABASE_URL=jdbc:mysql://host.docker.internal:3306/bd_magasin
COPY target/api_gw-0.0.1-SNAPSHOT.jar apigw.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar apigw.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar apigw.jar
