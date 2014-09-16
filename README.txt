Hi all.

Thanks for reading it. 
If you want run this project it is very simple to do. 
First mvn clean package server and put server.war to your's two tomcat's. 
For me they are simmilar only with different port (8080 and 8181). 
After that change url to tomcats in /client/src/main/resources/hosts files. 
Mind that if you are not running tomcat under IDE (i do it from Idea) then you possibly will need specify
full url (e.g. http://localhost:8080/server)
