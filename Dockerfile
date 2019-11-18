From openjdk:8    
ADD target/onlinebank-0.0.1-SNAPSHOT.jar onlinebank.jar
ENTRYPOINT ["java","-jar","onlinebank.jar"]
EXPOSE 8080