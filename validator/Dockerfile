FROM centos
RUN yum install -y java
RUN yum install -y sqlite
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} validator.jar
COPY sample.db sample.db
ENTRYPOINT ["java","-jar","/validator.jar"]