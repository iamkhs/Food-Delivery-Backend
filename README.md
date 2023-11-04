# Food Delivery Backend

# Live link🚀 - https://food-delivery23.netlify.app/

This is the backend REST API of a food delivery application, which allows users to order food. It is built using Java with Spring Boot, and incorporates several technologies and features to provide a seamless food ordering experience.

## Features

- **REST API**: Provides a RESTful API for seamless communication between the frontend and backend.

- **User Authentication**: Utilizes Spring Security for secure role-based authentication. Users can register, log in, and access their account.

- **Real-time Order Management**: Implements Spring Websocket to enable real-time order management for administrators. Admins can receive and handle orders as they come in.

- **Restaurant Geolocation**: Integrates Google Maps API for geocoding, enabling the system to find the nearest restaurants within a 2km radius.

- **Database**: Utilizes Spring Data JPA to interact with a MySQL database for storing user information, orders, and restaurant details, etc.

- **Admin Panel**: Administrators have the capability to add, delete, update food items, and accept or decline orders through the admin panel.

- **Caching Mechanism**: Implements Redis Cache for efficient caching to improve system performance.

- **Email Verification**: Users are required to verify their email addresses after registration. A verification link is sent to their email for confirmation.

- **JWT Authentication**: Implements JWT (JSON Web Tokens) for secure user authentication and authorization.

- **Cloudinary Integration**: Utilizes Cloudinary API for storing and managing food images.

- **Deployment**: Hosted on Microsoft Azure cloud for scalability and reliability.

## Technologies Used:

### Backend:

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL & Redis (for Caching)
- Google Maps API
- Cloudinary API

### Frontend:

- Angular (link🚀 - https://github.com/iamkhs/food-app-front-end)

## Configuration:

Before running the application, make sure to configure the following settings in your `application.properties` file:

- **MySQL Database Configuration:**
  - Set the database URL, username, and password in `application.properties` to connect to your MySQL database.

Example `application.properties`:

```properties
# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=root
spring.datasource.password=your_password
