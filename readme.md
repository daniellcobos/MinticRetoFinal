#What do i do with this repo?
Its just a demo of Spring Boot With MongoDB, download and then compile the Maven thing, if you are seeing this repo you should know how to run a Maven Project.

## You said this has a kafka producer i want to see it

Localhost:8085 *(if you are using your machine)* /api/user/{email}/{password} will send a message to a Kafka topic with the email and date of the attempt to log despite if its successful or not.
The Kafka topic its called *login_topic* and it tries to send it to a broker located at *localhost:9092* so you should have a basic Kafka broker running so the app works. 

### Wait the login its just sloppy api call

It was what the goverment asked me and my team to do, as this was done as an academic project to teach people programming