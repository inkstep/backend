FROM gradle:5.4.1-jdk8-alpine

# Install gradle 5.4.1
RUN gradle -v

# Copy files needed
COPY src/ src/
COPY .gradle/ .gradle/
COPY build.gradle build.gradle
COPY run run
COPY settings.gradle settings.gradle

USER root
RUN chown -R gradle .

RUN gradle build 

# Start Application
CMD sh run


