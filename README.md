# Shopkart Authorization OAuth2 Server

This is a Spring Boot OAuth2 Authorization Server for the Shopkart application.

## Prerequisites

- Java 17
- Maven
- Docker
- MySQL database

## Building the Application

To build the application, run:

```bash
mvn clean package
```

This will create a JAR file in the `target` directory.

## Docker Setup

This project includes a lightweight Docker setup with a multi-stage build process to minimize the final image size.

### Building the Docker Image

To build the Docker image, run:

```bash
docker build -t shopkart-auth-server .
```

### Running the Docker Container

To run the Docker container with default settings:

```bash
docker run -p 8080:8080 shopkart-auth-server
```

### Configuring Database Connection

By default, the application will try to connect to a MySQL database at `mysql:3306/shopkart_auth` with username `root` and password `password`.

To override these settings, you can pass environment variables:

```bash
docker run -p 8080:8080 \
  -e SERVER_PORT=8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://your-db-host:3306/your-db-name \
  -e SPRING_DATASOURCE_USERNAME=your-username \
  -e SPRING_DATASOURCE_PASSWORD=your-password \
  shopkart-auth-server
```

### Running with Docker Compose

For a complete setup including a MySQL database, use the provided Docker Compose file:

```bash
docker-compose up
```

This will:
1. Start a MySQL 8.0 database with the required configuration
2. Build and start the Shopkart Authorization OAuth2 Server
3. Configure all necessary environment variables
4. Set up health checks to ensure the database is ready before starting the application

The docker-compose.yml file includes:

```yaml
version: '3.8'

services:
  # MySQL Database
  mysql:
    image: mysql:8.0.33
    container_name: shopkart-auth-mysql
    environment:
      MYSQL_ROOT_PASSWORD: password
      MYSQL_DATABASE: shopkart_auth
    ports:
      - "3306:3306"
    volumes:
      - mysql-data:/var/lib/mysql
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost", "-u", "root", "-ppassword"]
      interval: 10s
      timeout: 5s
      retries: 5

  # Shopkart Authorization OAuth2 Server
  shopkart-auth:
    build: .
    container_name: shopkart-auth-server
    depends_on:
      mysql:
        condition: service_healthy
    environment:
      SERVER_PORT: 8080
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/shopkart_auth
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: password
    ports:
      - "8080:8080"
    restart: unless-stopped

volumes:
  mysql-data:
```

## Environment Variables

| Variable | Description | Default Value |
|----------|-------------|---------------|
| SERVER_PORT | The port the server runs on | 8080 |
| SPRING_DATASOURCE_URL | JDBC URL for the database | jdbc:mysql://mysql:3306/shopkart_auth |
| SPRING_DATASOURCE_USERNAME | Database username | root |
| SPRING_DATASOURCE_PASSWORD | Database password | password |

## Troubleshooting Docker Issues

### "Load metadata for docker.io" Error

If you encounter an error related to "load metadata for docker.io" when building or running Docker containers, try the following solutions:

1. **Check Docker Hub Connectivity**:
   ```bash
   curl -v https://registry-1.docker.io/v2/
   ```
   This should return a 200 OK response if your connection to Docker Hub is working.

2. **Use Specific Image Tags**:
   The Dockerfile now uses specific version tags for all images (e.g., `maven:3.9.0-eclipse-temurin-17-alpine` instead of `maven:3.9-eclipse-temurin-17-alpine`). This helps avoid issues with Docker Hub's image caching and metadata retrieval.

3. **Configure Docker to Use Alternative Registries**:
   If Docker Hub is experiencing issues, you can configure Docker to use alternative registries. Edit your Docker daemon configuration file (`/etc/docker/daemon.json` on Linux or `C:\ProgramData\docker\config\daemon.json` on Windows) and add:
   ```json
   {
     "registry-mirrors": [
       "https://registry.mirror1.io",
       "https://registry.mirror2.io"
     ]
   }
   ```
   Then restart Docker.

4. **Network Troubleshooting**:
   - Check if your network has any proxy settings that might be blocking Docker Hub
   - Verify that your firewall allows Docker to access the internet
   - Try using a different network connection

5. **Docker Hub Rate Limits**:
   Docker Hub has rate limits for pulling images. If you're hitting these limits, consider:
   - Authenticating with Docker Hub: `docker login`
   - Using a Docker Hub account with higher rate limits
   - Implementing a local Docker registry cache
