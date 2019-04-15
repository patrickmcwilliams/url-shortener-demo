FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN
COPY ./service/pom.xml /tmp/
COPY ./service/src /tmp/src/
WORKDIR /tmp/
RUN mvn package

FROM tomcat:9.0-jre8-alpine
RUN rm -rf $CATALINA_HOME/webapps/ROOT
COPY --from=MAVEN_TOOL_CHAIN /tmp/target/url-shortener*.war $CATALINA_HOME/webapps/ROOT.war

HEALTHCHECK --interval=1m --timeout=3s CMD wget --quiet --tries=1 --spider http://localhost:8080/ || exit 1