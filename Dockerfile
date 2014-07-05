FROM dockerfile/java
MAINTAINER Dennis Vriend <dnvriend@gmail.com>

ADD /target/scala-2.10/spray-markdown_2.10-0.1-SNAPSHOT-one-jar.jar /appl/
EXPOSE 8080
ENV BIND_ADDRESS 0.0.0.0
ENV BIND_PORT 8080
CMD java -jar /appl/spray-markdown_2.10-0.1-SNAPSHOT-one-jar.jar