# Microservice Architecture for Testing

Technologies: Spring Boot, Spring Cloud Netflix, Docker

The application is based on the data comming from https://www.anapioficeandfire.com. 
Many thanks to Joakim Skoog https://github.com/joakimskoog for the API.
The Api provides data from the Novel "A Song of Ice and Fire" by George R. R. Martin and as some maybe knows the story 
is driven by a lot of houses and their sworn members. With this application you can get the data from the API into a mongo db
and via a frontend application you can search for houses and swornmembers.

1. Microservice iceandfire-import: imports data from https://www.anapioficeandfire.com, converts data 
2. Microservice iceandfire-db: stores/gets data into/from a mongo database (Feign)
3. Microservice iceandfire-service: provide the imported data to the frontend
4. Microservice iceandfire-frontend: little web-app with Html, Bootstrap, JavaScript to search for Houses and SwornMembers
5. Zuul Proxy iceandfire-proxy
6. Eureka iceandfire-discovery
7. Springbootadmin
8. In the integration project you can find docker-compose files and file with run commands for running the containers one by one.

In the docker-compose.yml container for mongo and grafana with graphite are included.

All projects are Gradle pojects
