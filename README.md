# My_Breweries

Brewery is a basic school project based on Spring Boot.

It is an API allowing the users to do some call to an external API using specific authorization.

The chosen external API is [Open Brewery DB](https://www.openbrewerydb.org/). An API that repertories over 8000 breweries around the world.

## Installation

Clone the project.

```bash
git clone git@github.com:Periicles/apiritif_Brewery_EFREI.git
```
```bash
cd apiritif_Brewery_EFREI
```

## Usage

1. Rename the [application.properties.example](src/main/resources/application.properties.example) to `application.properties`.
2. Replace the needed variables to your own.
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/breweryDB
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
...
jwt.secret=your_super_secret_jwt_key
```
3. Launch your database with a schema named `BreweryDB`.
4. Finally run your Java application.

### Postman Collections

In root directory there's a collection `Breweries.postman_collections.json` you can import in Postman to test the API. In order to store the userToken after login you to have to add this post-request script :
```javascript
var jsonData = pm.response.json();
pm.environment.set("UserToken", jsonData.token);
```

### Roles

There is three different roles:
- `USER`
- `ADMIN`
- `SCRAPER`

### Endpoints

#### Scraper Endpoint

- **`GET /scraper/irish-breweries`**  
  Fetches and saves a list of Irish breweries from an external source.

#### User Endpoints

- **`GET /user/breweries/random`**  
  Retrieves a random selection of breweries.

- **`GET /user/breweries`**  
  Fetches the complete list of breweries. This operation may take some time.

#### Admin Endpoints

- **`GET /admin/breweries`**  
  Retrieves all stored Irish breweries from the database.

- **`POST /admin/breweries`**  
  Creates a new brewery.  
  **Request Body:** `BreweriesDTO` (JSON)

- **`PUT /admin/breweries/{id}`**  
  Updates an existing brewery by its ID.  
  **Path Parameter:** `id` (String)  
  **Request Body:** `BreweriesDTO` (JSON)

- **`DELETE /admin/breweries/{id}`**  
  Deletes a brewery by its ID.  
  **Path Parameter:** `id` (String)

- **`GET /admin/breweries/{id}`**  
  Retrieves a brewery by its ID.  
  **Path Parameter:** `id` (String)

### Dependencies

This controller relies on:

- `BreweriesService` for handling business logic.
- `BreweriesDTO` as a Data Transfer Object.

### Notes

- The `/scraper/irish-breweries` endpoint scrapes external data and saves it.
- User-related endpoints (`/user/breweries`) allow fetching data but do not modify it.
- Admin endpoints (`/admin/breweries`) allow full CRUD operations.



---
Project based on [Simon Bedard](https://github.com/JavaKhanStudio/Spring_Exemple_Security)'s project.