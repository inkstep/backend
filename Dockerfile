EXPOSE 8080

# Copy files needed
COPY src/ src/
COPY run run
COPY checkstyle.xml checkstyle.xml
COPY pom.xml

USER root
RUN chown -R gradle .

RUN gradle build

# Start Main
CMD sh run


