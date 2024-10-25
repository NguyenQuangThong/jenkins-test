FROM eclipse-temurin:21-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper files and build script
COPY gradlew build.gradle settings.gradle ./
COPY gradle/ gradle/

# Download the dependencies to be ready for offline use
#RUN ./gradlew build --no-daemon --stacktrace --refresh-dependencies

# Copy the source code
COPY src ./src

# Command to run the application using Gradle
CMD ["./gradlew", "bootRun", "--no-daemon"]
