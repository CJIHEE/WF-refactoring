FROM adoptopenjdk/openjdk11
VOLUME /tmp
COPY build/libs/employee-api-0.0.1.jar EmployeeApi.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "EmployeeApi.jar"]
