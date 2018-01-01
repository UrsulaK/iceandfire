FROM: Java:8
VOLUME: /temp
ADD: iceandfire_frontend.0.0.1-SNAPSHOT.jar app.jar
RUN: bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandome","-jar","/app.jar"]