### BUILD image
FROM maven:3-jdk-11 as builder
# create app folder for sources
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build
#Download all required dependencies into one layer
RUN mvn -B dependency:resolve dependency:resolve-plugins
#Copy source code
COPY src /build/src
# Build application
RUN mvn package

FROM tomcat:10.0.22-jdk11-corretto

COPY --from=builder /build/target/commandJ11-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/command.war

EXPOSE 8080

CMD ["catalina.sh", "run"]

