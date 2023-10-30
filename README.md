# RecipeManagementService-AWS


<p align="center"> 
    <a href="mySQL url" >
        <img alt="mySQL" src="https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white" />
    </a>
    <a href="Git Action url" >
        <img alt="Git Action" src="https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white" />
    </a>
    <a href="AWS url" >
        <img alt="AWS" src="https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white"/>
    </a>
</p> 

## Technologies Used
 **Framework:** Spring Boot
- **Language:** Java
- **Database:** MySQL
- **Build Tool:** Maven
- **Database:** MySQL (Hosted on AWS EC2)
- **Continuous Integration:** GitHub Actions


## Data Flow
1. **Recipe Management**:

    - A user creates a new recipe by sending a `POST` request to the `/api/recipes` endpoint, providing details such as the recipe's title, description, and category.

    - The system validates the request, creates the recipe entity, and associates it with the user who created it and the selected category.

    - The new recipe is saved in the database.

    - The system responds with a confirmation message, indicating the successful creation of the recipe.

    - Users can also update or delete their recipes by sending `PUT` or `DELETE` requests to the `/api/recipes/{recipeId}` endpoint.

    - The system checks if the provided recipe exists and is associated with the user.

    - If the recipe is valid, it is updated or deleted from the database, and the system sends a response confirming the update or deletion.

2. **Category Management**:

    - Users can create and manage recipe categories through API endpoints, such as creating a new category with a `POST` request to `/api/categories`.

    - The system manages recipe categories and their associations with recipes.

3. **Comment Management**:

    - Users can leave comments on their favorite recipes by sending a `POST` request to the `/api/comments` endpoint, specifying the comment text and the recipe to which it belongs.

    - The system validates the request, creates the comment entity, and associates it with the user who made the comment and the selected recipe.

    - The new comment is saved in the database, and the system responds with a confirmation message.

    - Users can also update or delete their comments by sending `PUT` or `DELETE` requests to the `/api/comments/{commentId}` endpoint.

    - The system checks if the provided comment exists and is associated with the user.

    - If the comment is valid, it is updated or deleted from the database, and the system sends a response confirming the update or deletion.

4. **User Authentication and Authorization**:

    - User authentication and authorization are handled securely to ensure that only authorized users can create, update, delete, and comment on recipes.

    - The system manages user accounts and ensures that user data is protected.

5. **Data Persistence**:

    - The application relies on a MySQL database for data storage. Entities such as recipes, users, comments, categories, and others are mapped to their corresponding database tables.

6. **RESTful API Endpoints**:

    - RESTful API endpoints provide a clear interface for users to interact with the application. These endpoints are documented using Swagger UI, providing a user-friendly way to explore and use the API.

7. **Database Design**:

    - The database design includes tables for recipes, users, comments, categories, and other entities, ensuring a structured and efficient data storage approach.





## Data Structures Used
1. **Entities**:
    - **Recipe**: Represents a recipe with attributes like `recipeId`, `title`, `description`, a reference to the associated `user`, and a reference to the `category`.

    - **User**: Represents a user with attributes like `userId`, `username`, `password`, and a collection of `recipes`.

    - **Comment**: Represents a comment with attributes like `commentId`, `text`, a reference to the `user` who made the comment, and a reference to the `recipe` to which the comment is related.

    - **Category**: Represents a category with attributes like `categoryId`, `name`, and a collection of `recipes`.

2. **Repositories**:
    - JPA repositories for data access, including repositories for recipes, users, comments, categories, and other related entities.

3. **ArrayLists**:
    - ArrayLists are used for efficiently managing lists of entities. For example, a user's recipes are managed using a collection of `Recipe` entities within the `User` entity.


## Database Configuration

 'applications.property file'

```properties
spring.datasource.url=jdbc:mysql://<Public IP>:3306/RecipeManager
spring.datasource.username=root
spring.datasource.password=yourPassword
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

## Project Summary

The RecipeManagementService-AWS is a web-based service that provides users with a platform to create, manage, and share their favorite recipes. Built using Spring Boot and hosted on AWS (Amazon Web Services), this project exemplifies modern web application development with a focus on data-driven features, user authentication, and data management. It offers a wide range of functionalities, including recipe and category management, user authentication and authorization, and the ability to leave comments on recipes. The application is backed by a MySQL database hosted on an AWS EC2 instance, providing efficient and reliable data storage.

The RecipeManagementService-AWS project serves as a practical example of how to build a feature-rich web service that includes data management, user interactions, and secure authentication. It showcases the use of Spring Boot, MySQL, and AWS, offering developers insights into creating similar applications or extending this one with additional features. The project aims to provide a comprehensive and efficient solution for recipe management in a collaborative and user-friendly environment.

# Thank you.
