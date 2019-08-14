FROM openjdk:8
COPY gildedrose-0.0.1-SNAPSHOT.jar /build/libs/
ENTRYPOINT java -jar .