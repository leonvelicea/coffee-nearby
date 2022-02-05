package org.dev.test.coffeenearby;

import org.dev.test.coffeenearby.data.repositories.CoffeeShopRepository;
import org.dev.test.generated.model.CoffeeShopDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CoffeeNearbyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CoffeeNearbyIntegrationTest {

    public static final String TEST_GET_COFFEE_SHOPS_APP_URL = "http://localhost:%s/api/search-for-coffee?lat=%f&lng=%f";
    private static final String POSTGRES_POSTGIS_DOCKER_IMAGE = "postgis/postgis";

    @Container
    private static final GenericContainer<?> postgisContainer = new GenericContainer<>(POSTGRES_POSTGIS_DOCKER_IMAGE).withExposedPorts(5432).withEnv("POSTGRES_PASSWORD", "postgres").withEnv("POSTGRES_DB", "coffee_shops");

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private GeometryFactory geometryFactory;
    @Autowired
    private CoffeeShopRepository coffeeShopRepository;

    @DynamicPropertySource
    static void rabbitProperties(DynamicPropertyRegistry registry) {
        postgisContainer.start();
        registry.add("spring.datasource.url", () -> String.format("jdbc:postgresql://localhost:%d/coffee_shops", postgisContainer.getMappedPort(5432)));
    }

    @Test
    public void testGetCoffeeShopsNearby_NoContent() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CoffeeShopDTO[]> response = restTemplate.getForEntity(String.format(TEST_GET_COFFEE_SHOPS_APP_URL, randomServerPort, 47.146836, 27.583606), CoffeeShopDTO[].class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @Sql("test_data.sql")
    public void testGetCoffeeShopsNearby_Success() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CoffeeShopDTO[]> response = restTemplate.getForEntity(String.format(TEST_GET_COFFEE_SHOPS_APP_URL, randomServerPort, 45.643423, 25.592810), CoffeeShopDTO[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CoffeeShopDTO[] coffeeShops = response.getBody();
        assertNotNull(coffeeShops);
        assertEquals(3, coffeeShops.length);
        CoffeeShopDTO coffeeShop = coffeeShops[0];
        assertEquals("Coffee shop 1", coffeeShop.getName());
        assertTrue(coffeeShops[0].getDistance() < coffeeShops[1].getDistance());
        assertTrue(coffeeShops[1].getDistance() < coffeeShops[2].getDistance());
    }
}
