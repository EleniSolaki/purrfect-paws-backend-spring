# Purrfect Paws Backend - Spring Boot

This project is a Spring Boot-based backend application, part of a 10-day full-stack application build challenge. You can find the frontend [here](https://github.com/EleniSolaki/purrfect-paws-frontend-angular). This backend provides the core functionality and services for the application.

### Prerequisites

(JDK) - Version 11 or higher, Apache Maven, MySQL

Before running the application, you need to configure the database connection settings, as well as the email credentials in the configuration file `application.properties`. The values are in a hidden .env file. You need to initiate it with your own credentials.

### Building and Running the Application

Clone the repository or download the source code as a ZIP file. Navigate to the project's root directory. Build the application using Maven. 

The application contains real life animal information and should be uploaded in the MySql Workbench with the following query: 
`INSERT INTO animaladoption.animals ( age, breed, description, gender, image, name)
VALUES ( '3 years old', 'Bombay', 'Playful, obsessed with treats', 'Male', '/images/Spooky.jpg', 'Spooky'),
('3 years old', 'European shorthair', 'Curious  and a bit psycho. She survived FIP', 'Female', 'images/Zenobia.jpg', 'Zenobia'),
('3 years old', 'European shorthair', 'Shy, playful and human-centered', 'Female', 'images/Valkyrie.jpg', 'Valkyrie'),
('2 years old', 'Halfbreed Calico', 'Quirky and playful', 'Female', '/images/Freya.jpg', 'Freya'),
('7 years old', 'Aegean', 'Chill, absent minded lady', 'Female', 'images/Booloobina.jpg', 'Booloobina'),
( '2 years old', 'European shorthair', 'Cannot trust easily, but very friendly when she does', 'Female', '/images/Ellie.jpg', 'Ellie'),
( '3 years old', 'European shorthair', 'Independent, strong and very smart', 'Female', '/images/Frida.jpg', 'Frida'),
( '3 years old', 'European shorthair', 'Shy and hunter', 'Female', '/images/Frida2.jpg', 'Frida'),
( '13 years old', 'European shorthair', 'Proud, life-fighter and hungry', 'Female', '/images/Roza.jpg', 'Roza'),
( '1 years old', 'European shorthair', 'Cute, playful and very hungry', 'Female', '/images/Rozaki.jpg', 'Rozaki'),
( '2 years old', 'Swedish countryside', 'She is social, and a good hunter', 'Female', '/images/FräuleinVonBitterwäldchen.jpeg', 'Fräulein von Bitterwäldchen'),
( '2 years old', 'Swedish countryside', 'Shy, clumsy and weird', 'Female', '/images/Flächen.jpeg', 'Flächen'),
( '2 years old', 'Aegean', 'Wild and independent', 'Female', '/images/Silia.jpg', 'Silia'),
( '1 years old', 'Bombay', 'Reliant, attention-seeker', 'Male', '/images/Panthiras.jpg', 'Panthiras');`

After inserting the data, run the application. The backend server will start running at http://localhost:8080

### Testing
This project includes a suite of unit tests to ensure the correctness of the backend functionality. You can run the tests using the following command: `mvn test`

### Documentation
This project includes Swagger documentation to provide an interactive API documentation experience. Once the backend server is running, you can access the Swagger UI by navigating to the following URL in your browser: http://localhost:8080/swagger-ui.html
The Swagger UI page will display the available API endpoints, request/response models, and allow you to interact with the APIs directly.

