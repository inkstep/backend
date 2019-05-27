FROM openjdk:11

# Install gradle 5.4.1
RUN apt-get -y gradle 5.4.1

# Copy files needed
COPY src/ src/
COPY .gradle/ .gradle/
COPY build.gradle build.gradle
COPY run run
COPY settings.gradle settings.gradle

# Run build
RUN gradle build

# Start Application
CMD sh run


