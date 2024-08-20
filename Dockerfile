# Use Amazon Corretto as the base image for Java
FROM amazoncorretto:17-alpine

# Install Maven
RUN apk add --no-cache maven

# Set the working directory
WORKDIR /SeleniumTesting
COPY . /SeleniumTesting/

# Run Maven to build the project
RUN mvn clean install -DskipTests

# Set the entry point to run tests
CMD ["mvn", "test"]