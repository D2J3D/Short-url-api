FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY target/shortURL-0.0.1-SNAPSHOT.jar shortUrl.jar
ENTRYPOINT ["java","-jar","/shortUrl.jar"]