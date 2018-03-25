# Pull base image.
FROM ubuntu:16.04

# Install Java.
RUN apt-get update
RUN DEBIAN_FRONTEND=noninteractive apt-get -y install software-properties-common
RUN \
  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  add-apt-repository -y ppa:webupd8team/java && \
  apt-get update && \
  apt-get install -y oracle-java8-installer && \
  rm -rf /var/lib/apt/lists/* && \
  rm -rf /var/cache/oracle-jdk8-installer
COPY build/libs/e-mailer-0.0.1.jar /home/
RUN ls -ltr /home/
CMD java -jar /home/e-mailer-0.0.1.jar
