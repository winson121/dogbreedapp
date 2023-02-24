# dogbreedapp
In this project, I've created a Backend REST API that make use of get method to get data from https://dog.ceo/dog-api/ built using springboot. The data source also include an inmemory h2 database which you can perform a CRUD operation to. This project also include unit testing of the api with mockito and swagger api documentation. 

To test the program locally, download the `mydogbreedapp-0.0.1-SNAPSHOT.jar` file and run it using the command: `java -jar mydogbreedapp-0.0.1-SNAPSHOT.jar` from terminal/windows cmd and open the `http://localhost:7777/swagger-ui/index.html#/dog-breed-rest-controller` link in your browser. The API that end with v2 (e.g. `/api/breeds/v2`) is the API that perform CRUD operation on h2 in memory database. The API without v2 suffix is the API for getting data from `https://dog.ceo/dog-api/breed` public api

To see the in memory database schema and executing a query to check the data, you can open this link `http://localhost:7777/h2-console/` after running the program locally and connect to the h2 database from web browser.

To see the swagger api documentation online, copy this link [mydogbreedapi_swagger_doc](https://api.jsonbin.io/v3/b/63f8dcc8c0e7653a057ddc75?meta=false) and paste it to https://generator.swagger.io/ online swagger doc generator.

## Database schema
the schema of this database follows the structure of dog.ceo public api structure, in which a dog breed can have multiple subbreed and a dog subbreed can have multiple image. 

![dogbreed](https://user-images.githubusercontent.com/45975038/221231791-dd1c3b5e-988f-414b-9c82-c09945ce993b.png)

## Structure of the project
You can see the structure of the project in the following diagram.

![mydogbreedapp_diagram](https://user-images.githubusercontent.com/45975038/221236709-7ddbd30a-2aa8-4a0b-82f8-1104376749ef.jpg)
