# docker build . --tag sunspot-analyser-test
# docker run -p 8010:80 sunspot-analyser-test
# docker exec -it $container-id /bin/sh

# Step 1 - build API
FROM public.ecr.aws/amazoncorretto/amazoncorretto:11-al2-jdk as build
WORKDIR /app
COPY . /app
RUN chmod +x gradlew
RUN ./gradlew bootJar

# Stage 2 - run API
FROM public.ecr.aws/amazoncorretto/amazoncorretto:11-al2-jdk
COPY --from=build /app/build/libs/*.jar /app/api.jar
COPY config/application.yml /app/config/application.yml
COPY config/logback-spring.xml /app/config/logback-spring.xml
WORKDIR /app
EXPOSE 80
ENTRYPOINT ["java","-jar", "-XX:InitialRAMPercentage=50", "-XX:MaxRAMPercentage=75", "-Dfile.encoding=UTF-8", "-Duser.timezone=UTC", "api.jar"]
#ENTRYPOINT ["java","-jar", "-XX:InitialRAMPercentage=50", "-XX:MaxRAMPercentage=75", "-Dfile.encoding=UTF-8", "-Duser.timezone=UTC", "api.jar", "--debug"]