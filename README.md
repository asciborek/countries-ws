# Countries-WS simple Spring SOAP Webservice
## Contract-First approach 
In contract-first approach Java classes are generated from xsd schema file.
The file in this project is located in src/main/resources as countries.xsd
## Generating Java Classes
In order to generate java classes you need to run ```mvn clean compile```. 
The ```jaxb2-maven-plugin``` plugin will generate Java classes. If you use idea remember to mark
the generated source directory (target/generated-sources/jaxb)
## Sending requests
Sample request files can be found in sample_requests directory. You should send post request 
with the target url: ```http://localhost:8080/ws```. Remember to set ```Content-Type``` header value
as ```text/xml```.




