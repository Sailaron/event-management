# Event Management App [Open Source]

### Quick start

#### 1. Option: Run Docker container from Docker Hub
~~~
docker run --name event-management -p 8080:8080 -d --restart always iljap/event-management
~~~
#### 2. Option: Start local docker container via Docker Compose
~~~
git clone https://github.com/Sailaron/event-management.git
~~~
~~~
docker compose up -d
~~~
### Author

Ilja Pozdejevs

* [LinkedIn](https://www.linkedin.com/in/ilja-pozdejevs/?locale=en_US)
* [GitHub](https://github.com/Sailaron)

### About application
 
There are available next endpoints:  
* /homepage/ - page with navigation menu  
* /events/ - page to see events by date filter, add new events and share link to specific event  
* /events/{date} - page to see events on specific date, for example /events/2022-12-16  
* /events/{date}/{event_id} - page to specific event, link can be received using button Share on /events/ page  
* /visitors/ - page to see all possible visitors, add and remove visitors  
* /database/ - page to manage H2 database, please check src/main/resources/application.properties file for credentials  
