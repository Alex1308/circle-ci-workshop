FROM openjdk:8
COPY gilderrose.jar /build/libs/
ENTRYPOINT java -jar .