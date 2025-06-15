FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

ADD target/*.jar app.jar

# Environment variables with default values
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql-container:3306/shopkart_authorization_oauth2
ENV SPRING_DATASOURCE_USERNAME=AuthUser
ENV SPRING_DATASOURCE_PASSWORD=Auth@#123
ENV SERVER_PORT=9090


# Expose the application port
EXPOSE ${SERVER_PORT}

# Start the application
ENTRYPOINT ["java","-jar","app.jar"]
