FROM openjdk:9-jdk-slim-sid

# Add non-free support to apt
RUN sed -i -r 1s/"^(.+)$"/"\1 non-free"/g /etc/apt/sources.list

# Update ubuntu sources
RUN apt-get update

# On Debian "sid", This maps to fdk-aac 0.1.5.
RUN apt-get install libfdk-aac1

ADD . /app

# Set the Gradle wrapper as the entry point
ENTRYPOINT ["/app/gradlew"]
