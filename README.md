## News Redis Application
This repository contains a simple Spring Boot application that demonstrates the integration of Redis for storing and retrieving news data. The application consists of the following components:

Config: Configuration class responsible for setting up the Redis connection and template.

Controller: RESTful API controller for handling news-related operations.

Entity: News entity class representing the structure of news data.

Repository: Data Access Object (DAO) class for interacting with Redis and performing CRUD operations on news data.

## Docker Compose
The docker-compose.yml file is provided to simplify the deployment of the application along with a Redis container. Ensure you have Docker and Docker Compose installed. Modify the paths and configurations as needed.
