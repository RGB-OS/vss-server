# Use official Tomcat base image
FROM tomcat:jre17

# Copy WAR file
COPY app/build/libs/vss-1.0.war /usr/local/tomcat/webapps/vss.war

ENV vss.jdbc.url="jdbc:postgresql://postgres:5432/postgres"
ENV vss.jdbc.username=postgres
ENV vss.jdbc.password=12345678

EXPOSE 8080
CMD ["catalina.sh", "run"]
