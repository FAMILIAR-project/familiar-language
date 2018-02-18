FROM openjdk:8
MAINTAINER ?????

RUN mkdir familiar familiar/host
WORKDIR familiar

COPY ./familiar.standalone/target/FAMILIAR-Standalone-1.0.10-jar-with-dependencies.jar familiar.jar
VOLUME ./host

ENTRYPOINT ["java", "-jar", "familiar.jar"]
CMD []

# Start interactive shell:
#  $ docker run -it familiar:1.2

# mount host directory into the "host" directory inside the container:
#  $ docker run -v /path/to/local/directory:/familiar/host -it familiar

# Run a given script
#  $ docker run -v /path/to/local/directory:/familiar/host familiar \
                   host/script.fml
