# Coffee shops nearby

<p>Small API projects that searches for nearby locations based on given coordinates</p>
<p>!!docker is needed for this project!! as it uses TestContainers</p>

## Uses PostGIS - see https://github.com/nouhouari/spring-boot-postgis it was used as a starting point

<p>Use the docker-compose database and adminer from `docker/db/pg-adminer.yaml` to set up the postgres and adminer</p>
<p>There is a file with some test data located here: `src/test/resources/org/dev/test/coffeenearby/test_data.sql`</p>

# Getting Started

1. Create a postgresql database "coffee_shops": `create database coffee_shops`;
2. This command is necessary, it will be executed by the flyway: `CREATE EXTENSION Postgis`;
3. Start the application: `mvn spring-boot:run`
4. Execute a curl command to get nearby coffee shops: `curl --location --request GET 'http://localhost:8080/api/search-for-coffee?lat=45.643423&lng=25.592810'` or use the swagger ui to test: `http://localhost:8080/api/swagger-ui/index.html#/coffee-shop-controller/searchForCoffeeShops`