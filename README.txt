Hi all.

Thanks for reading it. 
If you want run this project it is very simple to do. 
First mvn clean package server and put server.war to your's two tomcat's. 
For me they are simmilar only with different port (8080 and 8181). 
After that change url to tomcats in /client/src/main/resources/hosts files. 
Mind that if you are not running tomcat under IDE (i do it from Idea) then you possibly will need specify
full url (in my case it is http://localhost:8080/server)

As client is very simple and there where no exact whishes how it should looks like, I've made few client calls from test. 
Have a look on them.

Any way in case of any questions. You know where to find me ;)
