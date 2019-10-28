FROM openjdk:8-jre-alpine

RUN wget https://github.com/jwilder/dockerize/releases/download/v0.3.0/dockerize-linux-amd64-v0.3.0.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-v0.3.0.tar.gz \
    && rm dockerize-linux-amd64-v0.3.0.tar.gz

COPY target/planet-service-1.0.0.jar .
CMD dockerize -wait tcp://mongodb:27017 -timeout 100s /usr/bin/java -Dlogging.path=/log/ -Xmx400m -Xms400m -jar planet-service-1.0.0.jar
EXPOSE 8080
