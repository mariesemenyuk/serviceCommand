FROM tomcat:10.0.22-jdk11-corretto

COPY target/commandJ11-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/command.war

EXPOSE 8080

CMD ["catalina.sh", "run"]

